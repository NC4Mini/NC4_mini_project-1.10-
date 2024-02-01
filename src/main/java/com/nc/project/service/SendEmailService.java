package com.nc.project.service;

import com.nc.project.dto.UserAccountDTO;

public interface SendEmailService {

    public UserAccountDTO createMailAndChangePassword(String userEmail, String userId);

    public void updatePassword(String str,String userId);

    public String getTempPassword();

    public void mailSend(UserAccountDTO mailDto);
}
