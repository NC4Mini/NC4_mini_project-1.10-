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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserServiceImpl userService;
    private final UserAccountRepository userAccountRepository;

    @Transactional
    @GetMapping("/profile")
    public String editProfile(Model model, UserAccountDTO userAccountDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userId = userDetails.getUsername();

        userAccountDto = userService.findUser(userId).toDTO();

        model.addAttribute("userAccountDto", userAccountDto);


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
        UserDetails userDetails = (UserDetails) principal;
        String userId = userDetails.getUsername();

        List<UserShpAddrDTO> userShpAddrDTOList = objectMapper.readValue(addrList, new TypeReference<List<UserShpAddrDTO>>() {
        });
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
        UserDetails userDetails = (UserDetails) principal;
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
        UserDetails userDetails = (UserDetails) principal;
        String userId = userDetails.getUsername();

        UserAccountDTO userAccountDto = new UserAccountDTO();
        userAccountDto = userService.findUser(userId).toDTO();

        System.out.println(curUserPw);
        System.out.println(userAccountDto.getUserPw());

        if (Objects.equals(curUserPw, userAccountDto.getUserPw())) {
            checkPw = true;
            return new ResponseEntity<>(checkPw, HttpStatus.OK);
        } else {
            checkPw = false;
            return new ResponseEntity<>(checkPw, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/login")
    public String loginView() {
        return "user/login.html";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserAccountDTO userAccountDTO) {
        Map<String, Object> response = new HashMap<>();
        int idCheck = userService.idCheck(userAccountDTO.getUserId());

        if (idCheck == 0) {
            response.put("success", false);
            response.put("message", "아이디를 확인하세요");
            return ResponseEntity.badRequest().body(response);
        }

        UserAccountDTO loginUser = userService.login(userAccountDTO);

        if (loginUser == null) {
            response.put("success", false);
            response.put("message", "비밀번호를 확인하세요");
            return ResponseEntity.badRequest().body(response);
        }

        // 성공한 경우에는 세션에 사용자 정보를 저장하거나 토큰을 생성하는 등의 작업 수행가능.

        response.put("success", true);
        return ResponseEntity.ok(response);
    }
}

