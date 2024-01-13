package com.nc.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item {
    @Id
    @Column (name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="item_name")
    private String itemNm;

    @Column(name="item_description")
    private String itemDetail;

    @Column(name="item_stock")
    private int stockNumber;

    @Column (name="item_stock")
    private String itemSellStatus;

    @Column (name="item_category")
    private String itemCategory;

    @Column (name="item_price")
    private int price;

    @Column (name="cart_item_id")
    private Long cartItemId;


}