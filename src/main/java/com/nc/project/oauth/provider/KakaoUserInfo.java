package com.nc.project.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{
    /*
     * 카카오 자원 서버에서 제공하는 사용자 정보
     * {
     *   kakao_account: {
     *       id: 1231231,
     *       profile: {
     *           nickname: 'aaa'
     *       },
     *       email: 'a@a.a'
     * }
     * */
    Map<String, Object> attributes;

    // 게시판에서 사용할 profile_nickname, account_email
    // 키로 사용할 정보가 담긴 객체를 가져와서 담아줄 Map
    Map<String, Object> properties;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.properties = (Map<String, Object>)attributes.get("kakao_account");
    }

    @Override
    public String getProviderId() {
        return this.attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return this.properties.get("email").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> profile = (Map<String, Object>)this.properties.get("profile");
        return profile.get("nickname").toString();
    }
}