package com.nc.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nc.project.dto.ItemFileDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="item_file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate

@IdClass(ItemFileId.class)
@SequenceGenerator(
        name = "itemFileSeqGenerator",
        sequenceName = "item_file_seq",
        initialValue = 1,
        allocationSize = 1
)
public class ItemFile {

    @Id
    @ManyToOne
    @JoinColumn(name="item_id")
    @JsonBackReference
    private Item item;

    @Id
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "itemFileSeqGenerator"
    )
    private long itemFileId;
    private String itemFileName;
    private String itemFilePath;
    private String itemFileOrigin;
    private String itemFileCate;
    // Main / Detail / Thumbnail
    private String itemType;

    public ItemFileDTO toDTO() {
        return ItemFileDTO.builder()
                .itemId(this.item.getItemId())
                .itemFileId(this.itemFileId)
                .itemFileName(this.itemFileName)
                .itemFilePath(this.itemFilePath)
                .itemFileOrigin(this.itemFileOrigin)
                .itemFileCate(this.itemFileCate)
                .itemType(this.itemType)
                .build();
    }


}

