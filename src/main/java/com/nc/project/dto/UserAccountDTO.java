package com.nc.project.dto;

import com.nc.project.entity.UserAccount;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserAccountDTO {

    private long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userTel;
    private String userBirth;
    private String userGender;
    private String userProfile;
    private String userEmail;

    private List<UserShpAddrDTO> userShpAddrDTOList;

    public UserAccount toEntity() {
        return UserAccount.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
                .userTel(this.userTel)
                .userBirth(LocalDate.parse(this.userBirth))
                .userGender(this.userGender)
                .userProfile(this.userProfile)
                .userEmail(this.userEmail)
                .userShpAddrList(new ArrayList<>())
                .build();
    }
}
