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
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    @Column(unique = true)
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userTel;
    private LocalDateTime userRegdate;
    private String role;

    public UserAccountDto toDTO() {
        return UserAccountDto.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
                .userEmail(this.userEmail)
                .userTel(this.userTel)
                .userRegdate(this.userRegdate.toString())
                .role(this.role)
                .build();
    }
}
