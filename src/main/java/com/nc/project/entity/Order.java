//package com.nc.project.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "order")
//@Getter
//@Setter
//public class Order {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "order_id")
//    private Long orderId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    private UserDetail userDetail;
//
//    private LocalDateTime orderDate; // 주문 일시
//
//    private Character orderStatus; // 주문 상태
//
//    private String order_Request; // 배송 요청 사항
//
//    // orphanRemoval ==> 부모 엔티티가 삭제될 때 자식객체도 함께 삭제되도록 설정
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<OrderItem> orderItemList = new ArrayList<>();
//
//}
