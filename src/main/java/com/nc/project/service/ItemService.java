package com.nc.project.service;

import com.nc.project.entity.Item;
import com.nc.project.repository.ItemRepository;

import java.util.List;

public interface ItemService {

    // 상품 등록
    public void addItem (Item item);

    // 상품 읽어오기 (1개)
    public Item getItem(Long itemId);

    // 상품 읽어오기 (모두)
    public List<Item> getItemList ();

}
