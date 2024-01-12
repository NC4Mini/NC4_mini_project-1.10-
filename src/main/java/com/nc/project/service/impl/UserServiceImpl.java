package com.nc.project.service.impl;

<<<<<<< HEAD
import com.nc.project.dto.UserDetailDto;
import com.nc.project.repository.UserAccountRepository;
=======
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.UserAccount;
import com.nc.project.entity.UserShpAddr;
import com.nc.project.repository.UserRepository;
>>>>>>> origin/feature/join
import com.nc.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void join(UserAccountDTO userDTO) {

        UserAccount userAccount = userDTO.toEntity();

        List<UserShpAddr> userShpAddrList = userDTO.getUserShpAddrDTOList().stream()
                .map(userShpAddrDTO -> userShpAddrDTO.toEntity(userAccount)).toList();

        userShpAddrList.stream().forEach(
                userShpAddr -> userAccount.addUserShaAddrList(userShpAddr)
        );

        userRepository.save(userAccount);
    }
}
