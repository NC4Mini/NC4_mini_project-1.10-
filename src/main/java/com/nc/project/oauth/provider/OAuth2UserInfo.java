package com.nc.project.oauth.provider;

// 여러가지 소셜 로그인을 대응하기 위한 인터페이스
public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}