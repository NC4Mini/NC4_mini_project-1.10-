package com.nc.project.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException exception)
    throws IOException, ServletException {
        String errorMessage = getExceptionMessage(exception);
        
        // UTF-8로 errorMessage 인코딩
        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        
        // 로그인 실패시 이동할 url 지정
        setDefaultFailureUrl("/user/login?error=true&errorMsg=" + errorMessage);
        
        super.onAuthenticationFailure(request, response, exception);
    }
    
    private String getExceptionMessage(AuthenticationException exception) {
        if(exception instanceof BadCredentialsException) {
            return "비밀번호 불일치";
        } else if(exception instanceof UsernameNotFoundException) {
            return "사용자 없음";
        } else if(exception.getMessage().contains("UserDetailsService returned null")) {
            return "사용자 없음";
        } else {
            return "확인되지 않은 에러";
        }
    }
}
