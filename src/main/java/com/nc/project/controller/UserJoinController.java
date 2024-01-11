package com.nc.project.controller;

import com.nc.project.dto.UserAccountDTO;
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

public class UserJoinController {
    private final UserService userService;

    // 회원가입 화면으로 이동
    @GetMapping("/join-view")
    public ModelAndView joinView() {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("user/join.html");

        return mav;
    }

    @PostMapping("/join")
    public ModelAndView join(UserAccountDTO userAccountDTO,
                             List<UserShpAddrDTO> userShpAddrDTOList) {
        userAccountDTO.setUserShpAddrDTOList(userShpAddrDTOList);

        userService.join(userAccountDTO);

        ModelAndView mav = new ModelAndView();

        mav.setViewName("user/login.html");

        return mav;
    }

}
