package com.nc.project.service.impl;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.UserAccount;
import com.nc.project.entity.UserShpAddr;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void join(UserAccountDTO userAccountDTO) {

        UserAccount userAccount = userAccountDTO.toEntity();

        List<UserShpAddr> userShpAddrList = userAccountDTO.getUserShpAddrDTOList().stream()
                .map(userShpAddrDTO -> userShpAddrDTO.toEntity(userAccount)).toList();

        userShpAddrList.stream().forEach(
                userShpAddr -> userAccount.addUserShaAddrList(userShpAddr)
        );

        userAccountRepository.save(userAccount);
    }

    @Override
    public int emailCheck(String userEmail) { return userAccountRepository.countByUserEmail(userEmail);}

    @Override
    public void modifyUser(UserAccountDTO userAccountDTO) {


        UserAccount userAccount = userAccountRepository.findByUserId(userAccountDTO.getUserId()).get();
        UserAccountDTO newUserAccountDto = userAccount.toDTO();

        if(!userAccountDTO.getUserPw().isBlank()) {
        userAccount.setUserPw(passwordEncoder.encode(userAccountDTO.getUserPw()));
        }

        userAccount.setUserName(userAccountDTO.getUserName());
        userAccount.setUserBirth(LocalDate.parse(userAccountDTO.getUserBirth()));
        userAccount.setUserEmail(userAccountDTO.getUserEmail());
        userAccount.setUserTel(userAccountDTO.getUserTel());
        userAccount.setUserGender(userAccountDTO.getUserGender());

        userAccount.getUserShpAddrList().get(0).setAddrZone(userAccountDTO.getUserShpAddrDTOList().get(0).getAddrZone());
        userAccount.getUserShpAddrList().get(0).setAddrBasic(userAccountDTO.getUserShpAddrDTOList().get(0).getAddrBasic());
        userAccount.getUserShpAddrList().get(0).setAddrDetail(userAccountDTO.getUserShpAddrDTOList().get(0).getAddrDetail());
    }

    @Override
    public UserAccount findUser(String userId) {
        UserAccount userAccount = userAccountRepository.findByUserId(userId).get();

        return userAccount;
    }

    @Override
    public void resignUser(UserAccountDTO userAccountDTO) {
        userAccountDTO.toEntity().getUserShpAddrList().clear();
        userAccountRepository.delete(userAccountDTO.toEntity());
    }


    @Transactional
    @Override
    public UserAccountDTO login(UserAccountDTO userAccountDTO) {
        // 아이디와 비밀번호로 사용자 조회
        Optional<UserAccount> userOptional = userAccountRepository.findByUserIdAndUserPw(userAccountDTO.getUserId(), userAccountDTO.getUserPw());

        // 사용자가 존재하지 않으면 로그인 실패
        if(userOptional.isEmpty()) {
            return null;
        }

        // 사용자가 존재하면 User 엔티티를 DTO로 변환하여 반환
        return userOptional.map(UserAccount::toDTO).orElse(null);
    }

    @Transactional
    @Override
    public int idCheck(String userId) {
        return userAccountRepository.countByUserId(userId);
    }

    public boolean userEmailCheck(String userId, String userEmail) {
        UserAccount userAccount = userAccountRepository.findByUserId(userId).get();
        if(userAccount != null && userAccount.getUserEmail().equals(userEmail)) {
            return true;
        }
        else {
            return false;
        }
    }


    public void updateUserPassword(String userId,String pw) {
        Optional<UserAccount> userAccount = userAccountRepository.findByUserId(userId);
        userAccount.get().setUserPw(pw);
    }
}
