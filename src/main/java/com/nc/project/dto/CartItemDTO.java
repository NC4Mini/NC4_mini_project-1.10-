package com.nc.project.dto;

import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.Item;
import jakarta.persistence.IdClass;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CartItemDTO {
    private long cartId;
    private long cartItemId;
    private cart cartItem;
    private int cartItemCnt;

    public CartItem toEntity() {
        return CartItem.builder()
                .cartItemId(this.cartItemId)
                .cartItem(this.cartItem)
                .cartItemCnt(this.cartItemCnt)
                .build();
    }
}
