package com.nc.project.service.impl;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.UserAccount;
import com.nc.project.entity.UserShpAddr;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public void modifyUser(UserAccountDTO userAccountDTO) {
        userAccountRepository.save(userAccountDTO.toEntity());
    }

    @Override
    public void resignUser(UserAccountDTO userAccountDTO) {
        userAccountRepository.delete(userAccountDTO.toEntity());
    }


}
