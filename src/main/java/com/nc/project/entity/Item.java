package com.nc.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nc.project.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @Column (name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @Column(name="item_name")
    private String itemName;

    @Column(name="item_description")
    private String itemDescription;

    @Column (name="item_price")
    private int itemPrice;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ItemFile> itemFileList;

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
                .itemFileDTOList(this.itemFileList.stream().map(ItemFile::toDTO).toList())
                .build();
    }

    public void addItemFileList(ItemFile itemFile) {
        this.itemFileList.add(itemFile);
    }

}