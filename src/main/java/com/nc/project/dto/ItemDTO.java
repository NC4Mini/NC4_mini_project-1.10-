package com.nc.project.dto;

import com.nc.project.entity.Item;
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
    private int itemPrice;

    public Item toEntity() {
        return Item.builder()
                .itemId(this.itemId)
                .itemName(this.itemName)
                .itemDescription(this.itemDescription)
                .itemPrice(this.itemPrice)
                .build();
    }
}
