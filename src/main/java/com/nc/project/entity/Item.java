package com.nc.project.entity;

import com.nc.project.dto.ItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    private String itemName;
    private String itemDescription;
    private int itemStock;
    private char itemStatus;
    private String itemCategory;
    private int itemPrice;
    private String photo;

    @OneToMany(mappedBy = "item")
    private List<CartItem> itemList = new ArrayList<>();

//    public ItemDTO toDTO() {
//        return ItemDTO.builder()
//                .itemId(this.itemId)
//                .itemName(this.itemName)
//                .itemDescription(this.itemDescription)
//                .itemStock(this.itemStock)
//                .itemStatus(this.itemStatus)
//                .itemCategory(this.itemCategory)
//                .itemPrice(this.itemPrice)
//                .build();
//    }
}