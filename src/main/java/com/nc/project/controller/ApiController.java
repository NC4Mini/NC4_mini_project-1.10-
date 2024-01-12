package com.nc.project.controller;

import com.nc.project.dto.UserAccountDTO;
import com.nc.project.dto.UserShpAddrDTO;
import com.nc.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final UserService userService;

    @GetMapping("/test-api1")
    public UserAccountDTO testAPi1() {
        UserAccountDTO userAccountDTO = UserAccountDTO.builder()
                .id(1)
                .userId("test")
                .userPw("!dkdlxl1234")
                .userName("api test")
                .userTel("01012345678")
                .userBirth("2012/06/12")
                .userGender("man")
                .userProfile("test")
                .userEmail("aaa@aaa.com")
                .userShpAddrDTOList(new ArrayList<>())
                .build();

        return userAccountDTO;
    }

    @PostMapping("/test-join")
    public ResponseEntity<?> testJoin(@RequestBody UserAccountDTO userAccountDTO) {
        System.out.println(userAccountDTO);
//        userAccountDTO.setUserShpAddrDTOList(userShpAddrDTOList);

        userService.join(userAccountDTO);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
    }
}


