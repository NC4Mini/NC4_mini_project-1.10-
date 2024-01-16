package com.nc.project.entity;


<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonBackReference;
=======
import com.fasterxml.jackson.annotation.JsonManagedReference;
>>>>>>> origin/feature/cart
import com.nc.project.dto.CartDTO;
import jakarta.persistence.*;
import lombok.*;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

>>>>>>> origin/feature/cart
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
<<<<<<< HEAD
    @JoinColumn(name = "ID")
    @JsonBackReference
    private UserAccount userAccount;
=======
    @JoinColumn(name = "id")
    private UserDetail userDetail;
>>>>>>> origin/feature/cart

    // cartItem과 OneToMany 관계
    @OneToMany (mappedBy = "cart")
    @JsonManagedReference
    private List<CartItem> cartItemList = new ArrayList<>();

    // user 엔티티를 받아서 장바구니 엔티티를 생성하는 메서드
    public static Cart createCart(UserDetail userDetail) {
        Cart cart = new Cart();
        cart.setUserDetail(userDetail);
        return cart;
    }

    public CartDTO toDTO() {
        return CartDTO.builder()
                .id(this.userAccount.getId())
                .cartId(this.cartId)
                .cartItemDTOList(this.cartItemList.stream().map(cartItem -> cartItem.toDTO()).toList())
                .build();
    }
}