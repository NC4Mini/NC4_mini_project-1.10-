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

    private long cartItemId;
    private long cartId;
    private long itemId;
    private int cartItemCnt;

    public CartItem toEntity(Cart cart, Item item) {
        return CartItem.builder()
                .cartItemId(this.cartItemId)
                .cart(cart)
                .item(item)
                .cartItemCnt(this.cartItemCnt)
                .build();
    }
}
