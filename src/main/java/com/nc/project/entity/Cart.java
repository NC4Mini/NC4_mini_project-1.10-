package com.nc.project.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cart {
    // 키값
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    // cart와 user OneToOne 관계
    @OneToOne
    @JoinColumn(name = "id")
    private UserDetail userDetail;

    // cartItem과 OneToMany 관계
    @OneToMany (mappedBy = "cart")
    private List<CartItem> cartItemList = new ArrayList<>();

    public static Cart createCart(UserDetail userDetail) {
        Cart cart = new Cart();
        cart.setUserDetail(userDetail);
        return cart;
    }


//    public CartDTO toDTO() {
//        return CartDTO.builder()
//                .id(this.userDetail.getId())
//                .cartId(this.cartId)
////                .cartItemDTOList(this.cartItemList.stream().map(cartItem -> cartItem.toDTO()).toList())
//                .build();
//    }
}