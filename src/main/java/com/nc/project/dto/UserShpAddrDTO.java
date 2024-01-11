package com.nc.project.dto;

import com.nc.project.entity.User;
import com.nc.project.entity.UserShpAddr;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserShpAddrDTO {

    private int addrId;
    private long id;
    private String basicAddr;
    private String detailAddr;
    private String postAddr;
    private char defaultAddr;

    public UserShpAddr toEntity(User user) {
        return UserShpAddr.builder()
                .addrId(this.addrId)
                .user(user)
                .basicAddr(this.basicAddr)
                .detailAddr(this.detailAddr)
                .postAddr(this.postAddr)
                .defaultAddr(this.defaultAddr)
                .build();
    }
}
