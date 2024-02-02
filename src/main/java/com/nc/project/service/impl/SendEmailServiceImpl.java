package com.nc.project.service.impl;

import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService {

    private final UserAccountRepository userAccountRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender mailSender;

    @Value("${FROM_ADDRESS}")
    private String FROM_ADDRESS ;


    public UserAccountDTO createMailAndChangePassword(String userEmail, String userId){
        String str = getTempPassword();
        UserAccount userAccount = userAccountRepository.findByUserId(userId).get();
        String userName = userAccount.getUserName();
        UserAccountDTO dto = new UserAccountDTO();
        dto.setUserEmail(userEmail);
        dto.setUserId(userName+"님의 임시비밀번호 안내 이메일 입니다.");
        dto.setUserProfile("안녕하세요. 임시비밀번호 안내 관련 이메일 입니다." + "[" + userName + "]" +"님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(str,userId);
        return dto;
    }

    public void updatePassword(String str,String userId){
        String pw = passwordEncoder.encode(str);

        userService.updateUserPassword(userId,pw);
    }

    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(UserAccountDTO mailDto){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailDto.getUserEmail());
        message.setFrom(FROM_ADDRESS);
        message.setSubject(mailDto.getUserId());
        message.setText(mailDto.getUserProfile());

        mailSender.send(message);
    }
}
