//package com.nc.project.dto;
//
//import com.nc.project.entity.Cart;
//import com.nc.project.entity.CartItem;
//import com.nc.project.entity.Item;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString
//public class CartDTO {
//
//    private long id;
//    private long cartId;
//    private List<CartItem> cartItemList;
//
//    public Cart toEntity() {
//        return Cart.builder()
//                .id(this.id)
//                .cartId(this.cartId)
//                .cartItemList()
//                .build();
//    }
//}
