package com.nc.project.dto;

import com.nc.project.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {
    private long id;
    private String userId;
    private String userPw;
    private UserDetailDTO userDetailDTO;
    private List<UserShpAddrDTO> userShpAddrDTOList;

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
                .userShpAddrList(new ArrayList<>())
                .build();
    }
}
