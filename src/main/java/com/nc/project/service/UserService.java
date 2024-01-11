package com.nc.project.service;


import com.nc.project.dto.UserDetailDto;

public interface UserService {
    void resignUser(UserDetailDto userDetailDTO);

    void modifyUser(UserDetailDto userDetailDTO);

}
