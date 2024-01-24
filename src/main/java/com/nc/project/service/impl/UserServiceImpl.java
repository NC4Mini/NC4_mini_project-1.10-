package com.nc.project.service.impl;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.UserAccount;
import com.nc.project.entity.UserShpAddr;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAccountRepository userAccountRepository;
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
        userAccountRepository.save(userAccountDTO.toEntity());
    }

    @Override
    public UserAccount findUser(String userId) {
        UserAccount userAccount = userAccountRepository.findByUserId(userId).get();

        return userAccount;
    }

    @Override
    public void resignUser(UserAccountDTO userAccountDTO) {
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
}
