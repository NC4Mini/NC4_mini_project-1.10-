package com.nc.project.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.project.dto.ResponseDTO;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.dto.UserShpAddrDTO;
import com.nc.project.service.UserService;
import com.nc.project.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.sql.model.ModelMutationLogging;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserJoinController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // url : /user로 접근시 "/" 페이지로 리다이렉션(이동)
    @GetMapping
    public ModelAndView defaultMapping() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");

        ModelAndView mav = new ModelAndView(redirectView);
        return mav;
    }

    // 회원가입 화면으로 이동(GET - header.html)
    @GetMapping("/join")
    public ModelAndView joinView() {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("user/join.html");

        return mav;
    }

    // 회원가입 로직 (POST - join.html)
    @PostMapping("/join")
    public ResponseEntity<?> join(UserAccountDTO userAccountDTO,
                                  UserShpAddrDTO userShpAddrDTO) {
        ResponseDTO<Map<String, String>> response = new ResponseDTO<>();
        try {
            // 기본타입들은 null값을 가질 수 없다. 0이 기본값임

            if(userShpAddrDTO.getAddrStandard() == 0) {
                userShpAddrDTO.setAddrStandard('N');
            }

            List<UserShpAddrDTO> userShpAddrDTOList = new ArrayList<>();
            userShpAddrDTOList.add(userShpAddrDTO);

            userAccountDTO.setUserShpAddrDTOList(userShpAddrDTOList);
            userAccountDTO.setUserPw(passwordEncoder.encode(userAccountDTO.getUserPw()));
            userService.join(userAccountDTO);

            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("msg", "회원가입 성공");
            response.setItem(returnMap);
            response.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setErrorCode(605);
            response.setErrorMessage(e.getMessage());

            response.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(response);
        }
    }

    // 아이디 중복체크
    @PostMapping("/id-check")
    public ResponseEntity<?> idCheck(UserAccountDTO userAccountDTO) {
        ResponseDTO<Map<String, String>> response = new ResponseDTO<>();

        Map<String, String> returnMap = new HashMap<>();

        try {
            int idCheck = userService.idCheck(userAccountDTO.getUserId());

            // DB에 존재하지 않으면 0이 리턴됨
            if (idCheck == 0) {
                returnMap.put("idCheckMsg", "idOk");
            } else {
                returnMap.put("idCheckMsg", "idFail");
            }

            response.setItem(returnMap);
            response.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrorCode(501);
            response.setErrorMessage(e.getMessage());
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/email-check")
    public ResponseEntity<?> emailCheck(UserAccountDTO userAccountDTO) {
        ResponseDTO<Map<String, String>> response = new ResponseDTO<>();

        Map<String, String> returnMap = new HashMap<>();

        try {
            int emailCheck = userService.emailCheck(userAccountDTO.getUserEmail());

            // DB에 존재하지 않으면 0이 리턴됨
            if (emailCheck == 0) {
                returnMap.put("emailCheckMsg", "emailOk");
            } else {
                returnMap.put("emailCheckMsg", "emailFail");
            }

            response.setItem(returnMap);
            response.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setErrorCode(501);
            response.setErrorMessage(e.getMessage());
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(response);
        }
    }

}
