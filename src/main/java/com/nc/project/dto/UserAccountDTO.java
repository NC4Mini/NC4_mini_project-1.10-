package com.nc.project.dto;

import com.nc.project.entity.UserAccount;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class UserAccountDTO {

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

    public UserAccount toEntity() {
        return UserAccount.builder()
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
