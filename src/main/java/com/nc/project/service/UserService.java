package com.nc.project.service;


import com.nc.project.dto.UserAccountDto;
import com.nc.project.entity.UserAccount;

public interface UserService {
    void resignUser(UserAccountDto userAccountDTO);

    void modifyUser(UserAccountDto userAccountDTO);


}
