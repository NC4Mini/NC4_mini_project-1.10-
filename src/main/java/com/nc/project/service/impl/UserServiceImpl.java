package com.nc.project.service.impl;

import com.nc.project.dto.UserAccountDto;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public void modifyUser(UserAccountDto userAccountDto) {
        userAccountRepository.save(userAccountDto.toEntity());
    }

    @Override
    public void resignUser(UserAccountDto userAccountDto) {
        userAccountRepository.delete(userAccountDto.toEntity());
    }
}
