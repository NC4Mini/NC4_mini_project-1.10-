package com.nc.project.entity;


import com.nc.project.dto.CartDTO;
import com.nc.project.dto.CartItemDTO;
import com.nc.project.dto.ItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CART")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    // 키값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    // cartItem과 OneToMany 관계
    @OneToMany
    @JsonManagedReference
    private List<CartItem> cartItemList;

    // cart와 user OneToOne 관계
    @OneToOne
    @JoinColumn(name = "ID")
    @JsonBackReference
    private UserDetail userDetail;

    public CartDTO toDTO() {
        return CartDTO.builder()
                .id(this.userDetail.getId())
                .cartId(this.cartId)
                .cartItemDTOList(this.cartItemList.stream().map(cartItem -> cartItem.toDTO()).toList())
                .build();
    }
}