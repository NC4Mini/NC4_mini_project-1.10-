package com.nc.project.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nc.project.dto.CartDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CART")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cart {
    // 키값
    @Id
    @Column(name = "CART_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

//    // cartItem과 OneToMany 관계
//    @OneToMany
//    @JsonManagedReference
//    private List<CartItem> cartItemList;

    // cart와 user OneToOne 관계
    @OneToOne
    @JoinColumn(name = "ID")
    @JsonBackReference
    private UserAccount userAccount;

    public CartDTO toDTO() {
        return CartDTO.builder()
                .id(this.userAccount.getId())
                .cartId(this.cartId)
//                .cartItemDTOList(this.cartItemList.stream().map(cartItem -> cartItem.toDTO()).toList())
                .build();
    }
}