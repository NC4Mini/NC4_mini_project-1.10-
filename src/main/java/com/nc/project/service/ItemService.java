package com.nc.project.service;

import com.nc.project.dto.ItemDTO;
import com.nc.project.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ItemService {

    void insertItem(ItemDTO itemDTO);

    void modifyItem(ItemDTO itemDTO);

    void deleteItem(long itemId);

    // 해당 상품 읽어오기 (1개) - 상세 페이지
    public Item getItem(long itemId);

    // 상품 읽어오기 (모두)
    public List<Item> getItemList ();

    // 상품 검색하기
    public Page<Item> ItemSearchList(String searchKeyword, Pageable pageable);

    public Page<Item> ItemListRandom(Pageable pageable);
    public Page<Item> ItemList(Pageable pageable);

}
