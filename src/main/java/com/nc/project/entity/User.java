package com.nc.project.entity;

import com.nc.project.dto.UserDTO;
//import com.nc.project.dto.UserDetailDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue (
            strategy = GenerationType.IDENTITY
    )

    private long id;
    @Column(unique = true)
    private String userId;
    private String userPw;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private UserDetail userDetail;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<UserShpAddr> userShpAddrList;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
//                .userDetailDTO(this.userDetail.toDTO())
//                .userShpAddrDTOList(this.userShpAddrList.stream().map(UserShpAddr::toDTO).toList())
                .build();
    }
}
