package com.nc.project.dto;

import com.nc.project.entity.Item;
import com.nc.project.entity.ItemFile;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ItemFileDTO {
    private long itemId;
    private long itemFileId;
    private String itemFileName;
    private String itemFilePath;
    private String itemFileOrigin;
    private String itemFileCate;
    private String itemType;

    public ItemFile toEntity(Item item) {
        return ItemFile.builder()
                .item(item)
                .itemFileId(this.itemFileId)
                .itemFileName(this.itemFileName)
                .itemFilePath(this.itemFilePath)
                .itemFileOrigin(this.itemFileOrigin)
                .itemFileCate(this.itemFileCate)
                .itemType(this.itemType)
                .build();
    }
}
