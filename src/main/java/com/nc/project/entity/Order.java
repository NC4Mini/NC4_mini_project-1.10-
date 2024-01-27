package com.nc.project.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Builder;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private UserAccount userAccount;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<Item> orderItemList;
    
    @Column(name = "total_price")
    private double totalPrice;
    
    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "order_status")  // 0 - 주문중, 1 - 주문완료, 2 - 주문취소
    private int orderStatus;


}
