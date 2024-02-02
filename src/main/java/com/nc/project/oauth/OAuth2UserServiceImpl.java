package com.nc.project.oauth;

import com.nc.project.entity.CustomUserDetails;
import com.nc.project.entity.UserAccount;
import com.nc.project.entity.UserShpAddr;
import com.nc.project.oauth.provider.KakaoUserInfo;
import com.nc.project.oauth.provider.OAuth2UserInfo;
import com.nc.project.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import com.nc.project.dto.UserAccountDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;

    // 소셜 로그인 버튼 클릭 -> 자원 서버로 요청을 보냄 -> 동의항목 표출 ->
    // 동의항목 동의하고 확인 클릭 -> 인증 서버에서 사용자에 대한 인증이 진행 ->
    // 인증이 완료되면 토큰 서버에서 토큰이 발행 -> 토큰을 이용해서 자원서버에 정보를 받아올 수 있다 ->
    // securitycofig에 설정된 userInfoEndpoint 지정된 Service 객체의 loadUser 라는 메소드가 자동 실행된다.
    // loadUser 메소드에서 받아온 정보를 어떻게 처리할지만 구현하면 된다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) {
        OAuth2User oAuth2User = super.loadUser(request);

        String userName = "";
        String providerId = "";

        OAuth2UserInfo oAuth2UserInfo = null;

        // 소셜 카테고리 검증
        if(request.getClientRegistration().getRegistrationId().equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
            //12331241234312
            providerId = oAuth2UserInfo.getProviderId();
            userName = oAuth2UserInfo.getName();
        } else {
            System.out.println("카카오 로그인만 지원합니다.");
            return null;
        }

        String provider = oAuth2UserInfo.getProvider();
        // kakao_13241234123
        String userId = provider + "_" + providerId;
        // 소셜 로그인은 비밀번호가 필요하지 않기 때문에 그냥 nickname 값을 암호화해서 입력
        String password = passwordEncoder.encode(oAuth2UserInfo.getName());
//        String userEmail = oAuth2UserInfo.getEmail();

        // 소셜 로그인했던 이력이 있는지 검사할 객체
        UserAccount userAccount;

        // 이미 한 번이상 소셜 로그인 진행한 사용자
        if(userAccountRepository.findByUserId(userId).isPresent()) {
            userAccount = userAccountRepository.findByUserId(userId).get();
            // 처음으로 소셜 로그인을 하는 사용자
        } else {
            userAccount = null;
        }

        String defaultBirth = "2000-01-01";
        String defaultEmail = "kakao@email.com";
        String defaultGender = "NONE";
        String defaultTel = "010-0000-0000";

        // 소셜 로그인한 이력이 없으면 회원가입 처리(소셜 로그인한 사용자 정보 DB에 저장)
        if(userAccount == null) {
            userAccount = UserAccount.builder()
                    .userId(userId)
                    .userName(userName)
                    .userPw(password)
                    .userBirth(LocalDate.parse(defaultBirth))
                    .userEmail(defaultEmail)
                    .userGender(defaultGender)
                    .userTel(defaultTel)
                    .build();

            userAccountRepository.save(userAccount);
        }

        return CustomUserDetails.builder()
                .userAccount(userAccount)
                .attributes(oAuth2User.getAttributes())
                .build();
    }
}