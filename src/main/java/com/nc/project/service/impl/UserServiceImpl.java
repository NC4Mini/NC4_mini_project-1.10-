package com.nc.project.service.impl;

import com.nc.project.dto.UserDetailDto;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public void modifyUser(UserDetailDto userDetailDto) {
        userAccountRepository.save(userDetailDto.toEntity());
    }

    @Override
    public void resignUser(UserDetailDto userDetailDto) {
        userAccountRepository.delete(userDetailDto.toEntity());
    }
}
