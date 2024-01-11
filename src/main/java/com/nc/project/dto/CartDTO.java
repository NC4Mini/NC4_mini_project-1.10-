package com.nc.project.dto;

import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.Item;
import com.nc.project.entity.UserDetail;
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

    private long id;
    private long cartId;
    private List<CartItemDTO> cartItemDTOList;

    public Cart toEntity(UserDetail userDetail) {
        return Cart.builder()
                .userDetail(userDetail)
                .cartId(this.cartId)
                .cartItemList(new ArrayList<>())
                .build();
    }
}
