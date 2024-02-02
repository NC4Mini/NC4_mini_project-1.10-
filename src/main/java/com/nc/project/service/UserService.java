package com.nc.project.service;


import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.UserAccount;

public interface UserService {
    void join(UserAccountDTO userAccountDTO);

    int idCheck(String userId);

    int emailCheck(String userEmail);

    void resignUser(UserAccountDTO userAccountDTO);

    void modifyUser(UserAccountDTO userAccountDTO);
    UserAccountDTO login(UserAccountDTO userAccountDTO);

    // User 객체 반환해주는 메서드
    UserAccount findUser (String userId);


    public boolean userEmailCheck(String userEmail, String userName);


    public void updateUserPassword(String userEmail,String pw);
}
