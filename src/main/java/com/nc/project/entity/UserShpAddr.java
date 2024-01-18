package com.nc.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nc.project.dto.UserShpAddrDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_shpaddr")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 복합키 클래스 지정
@IdClass(UserShpAddrId.class)
@SequenceGenerator(
        name = "userShpAddrSeqGenerator",
        sequenceName = "user_shp_addr_seq",
        initialValue = 1,
        allocationSize = 1
)
public class UserShpAddr {
    //aa
    @Id
    // 사용자 한명이 배송지가 다른 여러 주문을 가질 수 있으므로 가능하므로 N:1
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private UserAccount userAccount;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userShpAddrSeqGenerator"
    )
    private int addrId;
    private String addrBasic;
    private String addrDetail;
    private String addrZone;
    // 기본 배송지일시 Y / 아니면 N, 상태코드값 부여
    private char addrStandard;
    private String addrNickname;

    public UserShpAddrDTO toDTO() {
        return UserShpAddrDTO.builder()
                .id(this.userAccount.getId())
                .addrId(this.addrId)
                .addrBasic(this.addrBasic)
                .addrDetail(this.addrDetail)
                .addrZone(this.addrZone)
                .addrStandard(this.addrStandard)
                .addrNickname(this.addrNickname)
                .build();
    }
}