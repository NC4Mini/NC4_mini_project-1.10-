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

//@IdClass(CartItemId.class)
public class CartItem {
    // cart와 ManyToOne 관계 (pk, fk)

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    @JsonBackReference
    private Cart cart;

    // 키 값 (pk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;

    // item과 ManyToOne 관계 (fk)
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    @JsonBackReference
    private Item item;

    @Column(name = "CART_ITEM_CNT")
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
