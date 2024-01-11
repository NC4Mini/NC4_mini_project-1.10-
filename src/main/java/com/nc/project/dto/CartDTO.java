package com.nc.project.dto;

import com.nc.project.entity.Cart;
import com.nc.project.entity.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CartDTO {

    private long cartId;

    public Cart toEntity() {
        return Cart.builder()
                .cartId(this.cartId)
                .build();
    }
}
