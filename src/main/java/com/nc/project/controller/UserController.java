package com.nc.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/edit-profile")
    public String editProfile() {
        return "/menu/editProfile";
    }

    @PostMapping("/editprofile/nickname-change")
    public void nicknameChange() {

    }

    @PostMapping("/editprofile/password-change")
    public void passwordChange() {

    }

    @PostMapping("/editprofile/user-delete")
    public void userDelete() {

    }

}
