package com.nc.project.dto;

import com.nc.project.entity.Cart;
import com.nc.project.entity.UserAccount;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CartDTO {

    private long id;
    private long cartId;
//    private List<CartItemDTO> cartItemDTOList;

    public Cart toEntity(UserAccount userAccount) {
        return Cart.builder()
                .userAccount(userAccount)
                .cartId(this.cartId)
//                .cartItemList(new ArrayList<>())
                .build();
    }
}
