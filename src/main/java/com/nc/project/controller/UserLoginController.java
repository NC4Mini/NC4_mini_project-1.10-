package com.nc.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserLoginController {

    @GetMapping("/login")
    public String userLogin() {
        return "/user/login";
    }
}
