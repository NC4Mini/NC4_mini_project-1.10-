package com.nc.project.entity;

import com.nc.project.dto.CartItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    // 키 값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;

    // item과 ManyToOne 관계
    @ManyToOne
    @JoinColumn(name = "CART_ID")
    @JsonBackReference
    private Cart cart;

    private int cartItemCnt;

    public CartItemDTO toDTO() {
        return CartItemDTO.builder()
                .cartId(this.cart.getCartId())
                .cartItemId(this.cartItemId)
                .cartItemList(this.cartItemList.stream().map(item -> item.toDTO().toList()))
                .cartItemCnt(this.cartItemCnt)
                .build();
    }
}
