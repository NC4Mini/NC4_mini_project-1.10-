package com.nc.project.entity;

//import com.nc.project.dto.CartItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nc.project.dto.CartItemDTO;
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

    // cart와 ManyToOne 관계
    @ManyToOne
    @JoinColumn(name = "CART_ID")
    @JsonBackReference
    private Cart cart;

    // item과 ManyToOne 관계
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    @JsonBackReference
    private Item item;

    private int cartItemCnt;

    public CartItemDTO toDTO() {
        return CartItemDTO.builder()
                .cartItemId(this.cartItemId)
                .cartId(this.cart.getCartId())
                .itemId(this.item.getItemId())
                .cartItemCnt(this.cartItemCnt)
                .build();
    }
}
