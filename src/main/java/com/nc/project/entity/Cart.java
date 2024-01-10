package com.nc.project.entity;//package com.example.tempproject.entity;
//
//import com.example.tempproject.dto.CartDTO;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "CART")
//@SequenceGenerator(
//        name = "CartSeqGenerator",
//        sequenceName = "CART_SEQ",
//        initialValue = 1,
//        allocationSize = 1
//)
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Setter
//public class Cart {
//    // 키값
//    @Id
//    @GeneratedValue(
//           strategy = GenerationType.SEQUENCE,
//            generator = "CartSeqGenerator"
//    )
//    private int cartNo;
//
//    private int cartItemsCnt;
//
//    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
//    @JsonBackReference
//    private User user;
//
//    @OneToMany(mappedBy = "cart")
//    @JsonBackReference
//    private Item item;
//
//    public CartDTO toDTO() {
//        return CartDTO.builder()
//                      .cartNo(this.cartNo)
//                      .cartItemCnt(this.cartItemsCnt)
//                      .build();
//    }
//}
