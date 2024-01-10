package com.nc.project.dto;

import com.nc.project.entity.UserAccount;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class UserAccountDto {

    private long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userTel;
    private String userRegdate;
    private String role;
    private String curUserPw;

    public UserAccount toEntity() {
        return UserAccount.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
                .userEmail(this.userEmail)
                .userTel(this.userTel)
                .userRegdate(LocalDateTime.parse(this.userRegdate))
                .role(this.role)
                .build();
    }
}
