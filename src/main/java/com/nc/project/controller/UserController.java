package com.nc.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/profile")
    public String editProfile() {
        return "/user/modify";
    }

    @PostMapping("/profile/nickname-change")
    public void nicknameChange() {

    }

    @PostMapping("/profile/password-change")
    public void passwordChange() {

    }

    @PostMapping("/profile/user-delete")
    public void userDelete() {

    }

}
