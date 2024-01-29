package com.nc.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Builder;

@Entity
@Table(name = "delivery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long ordersId;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonBackReference
    private Item item;
    
    @Column(name = "total_price")
    private double totalPrice;
    
    @Column(name = "delivery_time")
    private LocalDateTime orderTime;

    @Column(name = "delivery_status")  // 0 - 주문중, 1 - 주문완료, 2 - 주문취소
    private int orderStatus;


}
