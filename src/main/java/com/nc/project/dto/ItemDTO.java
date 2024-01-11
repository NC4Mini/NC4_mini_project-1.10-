package com.nc.project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ItemDTO {
    private long itemId;
    private String itemName;
    private String itemDescription;
    private int itemStock;
    private char itemStatus;
    private String itemCategory;
    private int itemPrice;

    public ItemDTO toEntity() {
        return ItemDTO.builder()
                .itemId(this.itemId)
                .itemName(this.itemName)
                .itemDescription(this.itemDescription)
                .itemStock(this.itemStock)
                .itemStatus(this.itemStatus)
                .itemCategory(this.itemCategory)
                .itemPrice(this.itemPrice)
                .build();
    }
}
