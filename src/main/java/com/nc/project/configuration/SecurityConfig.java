package com.nc.project.configuration;

import com.nc.project.handler.LoginFailureHandler;
import com.nc.project.oauth.OAuth2UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // 소셜 로그인 시 loadUser 메소드가 실행될 서비스 객체 생성
    private final OAuth2UserServiceImpl oAuth2UserService;

    // 로그인 실패 핸들러 객체 생성
    private final LoginFailureHandler loginFailureHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests.requestMatchers("/user/profile").authenticated();
                    authorizeRequests.requestMatchers("/board/board-list").authenticated();
                    authorizeRequests.requestMatchers("/item/item-add").authenticated();
                    authorizeRequests.requestMatchers("/item/item-manage").authenticated();
                    authorizeRequests.requestMatchers("/user/join").permitAll();
                    authorizeRequests.requestMatchers("/user/login").permitAll();
                    authorizeRequests.requestMatchers("/user/login-view").permitAll();
                    authorizeRequests.anyRequest().permitAll();
                })

                // 로그인, 로그아웃 설정
                // AuthenticationProvider객체에 전달할 사용자가 입력한 아이디와 비밀번호로
                // UsernamePasswordToken 생성해서 전달하는 과정을 구성
                .formLogin((formLogin) -> {
                    // 로그인 페이지 설정
                    formLogin.loginPage("/user/login");
                    // SpringSecurity에서는 기본적으로 username을 아이디로 password를 비밀번호로 사용
                    formLogin.usernameParameter("userId");
                    formLogin.passwordParameter("userPw");
                    // 로그인 처리하는 요청 url 지정
                    // 지정된 url의 요청이 왔을 때 SpringSecurity에서 인터셉트해서 인증처리를 알아서 진행
                    formLogin.loginProcessingUrl("/user/login");
                    // 로그인 실패 시 동작할 핸들러 클래스 등록
                    formLogin.failureHandler(loginFailureHandler);
                    // 로그인 성공 시 요청을 보낼 url 지정
                    formLogin.defaultSuccessUrl("/");
                })
                //OAuth2 기반 로그인 설정
                .oauth2Login((oauth2Login) -> {
                    oauth2Login.loginPage("/user/login-view");
                    // 자원 서버로부터 전달받은 사용자 정보에 대한 처리를 어느 클래스에서 할 것인지 지정
                    oauth2Login.userInfoEndpoint(userInfoEndpointConfig -> {
                        userInfoEndpointConfig.userService(oAuth2UserService);
                    });
                })
                // 로그아웃 처리
                .logout((logout) -> {
                    // 로그아웃 요청 url 지정
                    logout.logoutUrl("/logout");
                    // 사용자 인증정보가 저장된 security context가
                    // HttpSession에 저장되기 때문에 세션을 만료시켜서
                    // security contest를 제거한다.
                    logout.invalidateHttpSession(true);
                    // 로그아웃 성공 시 요청을 보낼 url 지정
                    logout.logoutSuccessUrl("/");
                })
                .build();

                /**
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests.requestMatchers("/").permitAll();
                    authorizeRequests.requestMatchers("/css/**").permitAll();
                    authorizeRequests.requestMatchers("/js/**").permitAll();
                    authorizeRequests.requestMatchers("/img/**").permitAll();
                    // /user/로 시작하는 요청
                    authorizeRequests.requestMatchers("/user/join").permitAll();
                    authorizeRequests.requestMatchers("/user/login").permitAll();
                    authorizeRequests.requestMatchers("/user/profile").authenticated();
                    // 권한으로 접근제어
                    // 게시판의 모든 요청 'USER', 'ADMIN' 권한을 가진 사용자만 접근 가능하도록 설정
                    authorizeRequests.requestMatchers("/board/**").hasAnyRole("USER", "ADMIN");
                    // 관리자 페이지에 대한 접근제어
                    authorizeRequests.requestMatchers("/admin/**").hasRole("ADMIN");
                    // 위에 설정한 요청외의 모든 요청은 인증된 사용자(로그인한 사용자)만 접근가능하도록 설정
                    authorizeRequests.anyRequest().authenticated();
                })
                **/

    }

}