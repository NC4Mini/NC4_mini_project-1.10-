package com.nc.project.entity;


import com.nc.project.dto.CartDTO;
import com.nc.project.dto.ItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CART")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    // 키값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    // cartItem과 oneToMany 관계
    @OneToMany
    @JoinColumn(name = "CART_ITEM")
    private List<CartItem> cartItemList;



//    // cart와 user onetoone 관계
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonBackReference
//    private User user;

    public CartDTO toDTO() {
        return CartDTO.builder()
                .cartId(this.cartId)
                .build();
    }
}