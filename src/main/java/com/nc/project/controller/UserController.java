package com.nc.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.dto.UserShpAddrDTO;
import com.nc.project.entity.CustomUserDetails;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserServiceImpl userService;
    private final UserAccountRepository userAccountRepository;

    @Transactional
    @GetMapping("/profile")
    public String editProfile(Model model,UserAccountDTO userAccountDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userId = userDetails.getUsername();

        userAccountDto = userService.findUser(userId).toDTO();

        model.addAttribute("userAccountDto",userAccountDto);


        return "/user/modify";
    }


    @Transactional
    @PostMapping("/change")
    @ResponseBody
    public void change(UserAccountDTO userAccountDto,
                       @AuthenticationPrincipal CustomUserDetails customUserDetails,
                       @RequestParam("addrList") String addrList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userId = userDetails.getUsername();

        List<UserShpAddrDTO> userShpAddrDTOList = objectMapper.readValue(addrList, new TypeReference<List<UserShpAddrDTO>>() {});
        UserAccountDTO originalUserAccountDTO = userService.findUser(userId).toDTO();

        userShpAddrDTOList.get(0).setId(originalUserAccountDTO.getId());
        userShpAddrDTOList.get(0).setAddrId(1);


        userAccountDto.setUserShpAddrDTOList(userShpAddrDTOList);



        userAccountDto.setUserId(originalUserAccountDTO.getUserId());

        userService.modifyUser(userAccountDto);
    }

    @Transactional
    @PostMapping("/resign")
    @ResponseBody
    public void resign(HttpSession session) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userId = userDetails.getUsername();

        UserAccountDTO userAccountDto = userService.findUser(userId).toDTO();

        userService.resignUser(userAccountDto);

        session.setAttribute("SPRING_SECURITY_CONTEXT", null);
    }

    @Transactional
    @PostMapping("/password-check")
    @ResponseBody
    public ResponseEntity<Boolean> passwordcheck(Model model, @RequestParam("curUserPw") String curUserPw, @RequestParam("checkPw") Boolean checkPw) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userId = userDetails.getUsername();

        UserAccountDTO userAccountDto = new UserAccountDTO();
        userAccountDto = userService.findUser(userId).toDTO();

        System.out.println(curUserPw);
        System.out.println(userAccountDto.getUserPw());

        if(Objects.equals(curUserPw, userAccountDto.getUserPw())) {
            System.out.println("비밀번호 일치");
            checkPw = true;
            return new ResponseEntity<>(checkPw, HttpStatus.OK);
        } else {
            checkPw = false;
            System.out.println("비밀번호 실패");
            return new ResponseEntity<>(checkPw, HttpStatus.NOT_ACCEPTABLE);
        }
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

