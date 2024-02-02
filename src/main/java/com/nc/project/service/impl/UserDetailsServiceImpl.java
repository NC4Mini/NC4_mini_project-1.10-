package com.nc.project.service.impl;

import com.nc.project.entity.CustomUserDetails;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAccountRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    // SpringSecurity 인증과정에서 자동으로 호출되는 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userOptional = userRepository.findByUserId(username);

        if(userOptional.isEmpty()) {
            return null;
        }

        return CustomUserDetails.builder()
                .userAccount(userOptional.get())
                .build();
    }
}
