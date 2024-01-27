package com.nc.project.entity;

import com.nc.project.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @Column (name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;

    @Column(name="item_name")
    private String itemName;

    @Column(name="item_description")
    private String itemDescription;

    @Column (name="item_price")
    private int itemPrice;

//    @Column(name="item_stock")
//    private int itemStock;

//    @Column (name="item_status")
//    private char itemStatus;

//    @Column (name="item_category")
//    private String itemCategory;

//    @Column (name="cart_item_id")
//    private Long cartItemId;

//    @OneToMany(mappedBy = "item")
//    private List<CartItem> itemList = new ArrayList<>();

    public ItemDTO toDTO() {
        return ItemDTO.builder()
                .itemId(this.itemId)
                .itemName(this.itemName)
                .itemDescription(this.itemDescription)
                .itemPrice(this.itemPrice)
                .build();
    }

}