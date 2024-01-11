package com.nc.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nc.project.dto.UserDetailDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_DETAIL")
@Builder
public class UserDetail {

    @Id
    @OneToOne
    @JoinColumn(name = "ID")
    @JsonBackReference
    private User user;
    private LocalDate userBirth;
    private String userGender;
    private String userName;
    private String userNickname;
    private String userProfile;
    private String userTel;

    public UserDetailDTO toDTO() {
        return UserDetailDTO.builder()
                .id(this.user.getId())
                .userBirth(this.userBirth.toString())
                .userName(this.userName)
                .userNickname(this.userNickname)
                .userProfile(this.userProfile)
                .userTel(this.userTel)
                .build();
    }
}
