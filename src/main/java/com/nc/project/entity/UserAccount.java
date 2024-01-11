package com.nc.project.entity;


import com.nc.project.dto.UserAccountDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true) private String userId;
    private String userPw;
    private String userName;
    private String userTel;
    private String userAddr;
    private LocalDateTime userBirth;
    private String userGender;
    private String userProfile;
    private String userNickname;

    public UserAccountDto toDTO() {
        return UserAccountDto.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
                .userTel(this.userTel)
                .userAddr(this.userAddr)
                .userBirth(this.userBirth.toString())
                .userGender(this.userGender)
                .userProfile(this.userProfile)
                .userNickname(this.userNickname)
                .build();
    }
}
