package com.nc.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nc.project.dto.CartDTO;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.transaction.annotation.Transactional;

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
    @JsonBackReference
    private UserAccount userAccount;

    // cartItem과 OneToMany 관계
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> cartItemList = new ArrayList<>();

    @Column(name = "cart_total_price")
    private int totalPrice;

    // totalPrice를 계산해주는 메서드
    public void calcTotalPrice() {
        int calcTotalPrice = 0;

        for (CartItem cartItem : cartItemList) {
            calcTotalPrice += cartItem.getItem().getItemPrice() * cartItem.getCartItemCnt();
        }
        this.totalPrice = calcTotalPrice;
    }

    public CartDTO toDTO() {
        return CartDTO.builder()
                .id(this.userAccount.getId())
                .cartId(this.cartId)
                .cartItemDTOList(this.cartItemList.stream().map(cartItem -> cartItem.toDTO()).toList())
                .totalPrice(this.totalPrice)
                .build();
    }
}