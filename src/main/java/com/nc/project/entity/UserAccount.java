package com.nc.project.entity;


import com.nc.project.dto.UserAccountDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
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
    private String userEmail;

    public UserAccountDTO toDTO() {
        return UserAccountDTO.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
                .userTel(this.userTel)
                .userAddr(this.userAddr)
                .userBirth(this.userBirth.toString())
                .userGender(this.userGender)
                .userProfile(this.userProfile)
                .userEmail(this.userEmail)
                .build();
    }
}
