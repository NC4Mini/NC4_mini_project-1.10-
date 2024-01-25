package com.nc.project.controller;

import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserServiceImpl userService;
    private final UserAccountRepository userAccountRepository;


    @GetMapping("/profile")
    public String editProfile() {
        return "/user/modify";
    }


    @PostMapping("/change")
//    public void change(UserAccountDto newUuserAccountDto) {
    public void change() {

        //테스트용 데이터
        Long id = 1L;

        UserAccountDTO originalUserAccountDTO = userAccountRepository.findById(id).get().toDTO();

        UserAccountDTO aaa= UserAccountDTO.builder()
                .id(id)
                .userId("11")
                .userPw("12341234")
                .userName("12341234")
                .userTel("12341234")
                .userBirth(originalUserAccountDTO.getUserBirth())
                .userGender("12341234")
                .userProfile("12341234")
                .userEmail("12341234")
//                .userId(newUuserAccountDto.getUserId())
//                .userPw(newUuserAccountDto.getUserPw())
//                .userName(newUuserAccountDto.getUserName())
//                .userTel(newUuserAccountDto.getUserTel())
//                .userAddr(newUuserAccountDto.getUserAddr())
//                .userBirth(newUuserAccountDto.getUserBirth())
//                .userGender(newUuserAccountDto.getUserGender())
//                .userProfile(newUuserAccountDto.getUserProfile())
//                .userEmail(newUuserAccountDto.getUserEmail())
                .build();



        originalUserAccountDTO = aaa;
        System.out.println(originalUserAccountDTO);
        userService.modifyUser(originalUserAccountDTO);
    }

    @Transactional
    @PostMapping("/resign")
    public void resign() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userId = userDetails.getUsername();

        UserAccountDTO userAccountDto = new UserAccountDTO();

        userAccountDto = userService.findUser(userId).toDTO();
        userService.resignUser(userAccountDto);
    }


    @GetMapping("/login")
    public String loginView() {
        return "user/login.html";
    }

    @PostMapping("/login")
    public String login(UserAccountDTO userAccountDTO, HttpSession session, Model model) {
        int idCheck = userService.idCheck(userAccountDTO.getUserId());

        if (idCheck == 0) {
            model.addAttribute("close", "userId");
            return "user/login.html";
        }

        UserAccountDTO loginUser = userService.login(userAccountDTO);

        if (loginUser == null) {
            model.addAttribute("close", "userPw");
            return "user/login.html";
        }

        session.setAttribute("loginUser", loginUser);

        System.out.println(session.getAttribute("loginUser"));
        return "redirect:/";

    }
}

