package com.nc.project.dto;

import com.nc.project.entity.UserDetail;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class UserDetailDto {

    private long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userTel;
    private String userAddr;
    private String userBirth;
    private String userGender;
    private String userProfile;
    private String userEmail;

    public UserDetail toEntity() {
        return UserDetail.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
                .userTel(this.userTel)
                .userAddr(this.userAddr)
                .userBirth(LocalDateTime.parse(this.userBirth))
                .userGender(this.userGender)
                .userProfile(this.userProfile)
                .userEmail(this.userEmail)
                .build();
    }
}
