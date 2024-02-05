package com.nc.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

// DB에서 사용자의 아이디와 비밀번호로 조회해온 데이터를 담아줄 객체
// UsernamePasswordToken의 정보들과 비교될 객체
// 비교해서 동일하면 리턴되다가 SecurityContextHolder에 등록될 객체
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails, OAuth2User {
    private UserAccount userAccount;

    // 소셜 로그인 시 사용자의 정보를 담아줄 컬렉션
    Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();

        auths.add(
                new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return null;
                    }
                }
        );

        return auths;
    }

    @Override
    public String getPassword() {
        return userAccount.getUserPw();
    }

    @Override
    public String getUsername() {
        return userAccount.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    // 소셜 로그인한 사용자의 정보를 가져오는 메소드
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
    // OAuth2User를 구현하기 위해 생성한 메소드. 사용하지 않음
    @Override
    public String getName() {
        return null;
    }
}
