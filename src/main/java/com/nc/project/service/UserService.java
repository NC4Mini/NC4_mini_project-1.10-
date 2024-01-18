package com.nc.project.service;
import com.nc.project.dto.UserAccountDTO;

public interface UserService {
    void join(UserAccountDTO userAccountDTO);

    int idCheck(String userId);

    int emailCheck(String userEmail);

    void resignUser(UserAccountDTO userAccountDTO);

    void modifyUser(UserAccountDTO userAccountDTO);

}

