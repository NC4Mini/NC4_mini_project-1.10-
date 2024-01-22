package com.nc.project.dto;

import com.nc.project.entity.Cart;
import com.nc.project.entity.UserAccount;
import lombok.*;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CartDTO {

    private long id;
    private long cartId;
    private List<CartItemDTO> cartItemDTOList;
    private int totalPrice;

    public Cart toEntity(UserAccount userAccount) {
        return Cart.builder()
                .userAccount(userAccount)
                .cartId(this.cartId)
                .cartItemList(new ArrayList<>())
                .totalPrice(this.totalPrice)
                .build();
    }
}
