package com.nc.project.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nc.project.dto.UserAccountDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.aspectj.weaver.ast.Or;

@Entity
@Table(name = "user_account")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {
    //aa
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true) private String userId;
    private String userPw;
    private String userName;
    private String userTel;
    private LocalDate userBirth;
    private String userGender;
    private String userProfile;
    private String userEmail;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<UserShpAddr> userShpAddrList;

    @OneToOne (mappedBy = "userAccount", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Cart cart;

    @OneToMany(mappedBy = "userAccount")
    @JsonManagedReference
    private List<Delivery> deliveryList;

    public UserAccountDTO toDTO() {
        return UserAccountDTO.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
                .userTel(this.userTel)
                .userBirth(this.userBirth.toString())
                .userGender(this.userGender)
                .userProfile(this.userProfile)
                .userEmail(this.userEmail)
                .userShpAddrDTOList(this.userShpAddrList.stream().map(UserShpAddr::toDTO).toList())
                .build();
    }

    public void addUserShaAddrList(UserShpAddr userShpAddr) {this.userShpAddrList.add(userShpAddr);}

}
