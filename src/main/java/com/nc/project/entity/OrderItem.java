//package com.nc.project.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "order_item")
//public class OrderItem {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "order_item_id")
//    private Long orderItemId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id")
//    private Item item;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    private int orderItemPrice; // 주문 상품 가격
//
//    private int orderItemCount; // 주문 상품 수량
//
//}
