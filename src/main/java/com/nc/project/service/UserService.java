package com.nc.project.service;


import com.nc.project.dto.UserAccountDto;

public interface UserService {
    void resignUser(UserAccountDto userAccountDTO);

    void modifyUser(UserAccountDto userAccountDTO);

}
