package com.nc.project.service.impl;

import com.nc.project.dto.UserDTO;
import com.nc.project.entity.User;
import com.nc.project.entity.UserShpAddr;
import com.nc.project.repository.UserRepository;
import com.nc.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public void join(UserDTO userDTO) {

        User user = userDTO.toEntity();

        List<UserShpAddr> userShpAddrList = userDTO.getUserShpAddrDTOList().stream()
                .map(userShpAddrDTO -> userShpAddrDTO.toEntity(user)).toList();

        userShpAddrList.stream().forEach(
                userShpAddr -> user.addUserShaAddrList(userShpAddr)
        );

        userRepository.save(user);
    }
}
