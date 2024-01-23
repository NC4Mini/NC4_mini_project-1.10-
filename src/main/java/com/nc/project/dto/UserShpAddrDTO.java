package com.nc.project.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nc.project.entity.UserAccount;
import com.nc.project.entity.UserShpAddr;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserShpAddrDTO {
    private int addrId;
    private long id;
    private String addrBasic;
    private String addrDetail;
    private String addrZone;
    private char addrStandard;
    private String addrNickname;

    public UserShpAddr toEntity(UserAccount userAccount) {
        return UserShpAddr.builder()
                .addrId(this.addrId)
                .userAccount(userAccount)
                .addrBasic(this.addrBasic)
                .addrDetail(this.addrDetail)
                .addrZone(this.addrZone)
                .addrStandard(this.addrStandard)
                .addrNickname(this.addrNickname)
                .build();
    }
}
