package com.nc.project.controller;

import com.nc.project.dto.UserDTO;
import com.nc.project.dto.UserDetailDTO;
import com.nc.project.dto.UserShpAddrDTO;
import com.nc.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    // 회원가입 화면으로 이동
    @GetMapping("/join-view")
    public ModelAndView joinView() {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("user/join.html");

        return mav;
    }

    @PostMapping("/join")
    public ModelAndView join(UserDTO userDTO, UserDetailDTO userDetailDTO,
                             List<UserShpAddrDTO> userShpAddrDTOList) {
        userDTO.setUserDetailDTO(userDetailDTO);
        userDTO.setUserShpAddrDTOList(userShpAddrDTOList);

        userService.join(userDTO);

        ModelAndView mav = new ModelAndView();

        mav.setViewName("user/login.html");

        return mav;
    }

}
