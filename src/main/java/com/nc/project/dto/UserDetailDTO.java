package com.nc.project.dto;

import com.nc.project.entity.User;
import com.nc.project.entity.UserDetail;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDTO {
    private long id;
    private String userBirth;
    private String userGender;
    private String userName;
    private String userNickname;
    private String userProfile;
    private String userTel;

    public UserDetail toEntity(User user) {
        return UserDetail.builder()
                .user(user)
                .userBirth(LocalDate.parse(this.userBirth))
                .userGender(this.userGender)
                .userName(this.userName)
                .userNickname(this.userNickname)
                .userProfile(this.userProfile)
                .userTel(this.userTel)
                .build();
    }
}
