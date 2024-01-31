package com.nc.project.dto;

import com.nc.project.entity.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<ItemFileDTO> itemFileDTOList;

    public Item toEntity() {
        return Item.builder()
                .itemId(this.itemId)
                .itemName(this.itemName)
                .itemDescription(this.itemDescription)
                .itemPrice(this.itemPrice)
                .itemFileList(new ArrayList<>())
                .build();
    }
}
