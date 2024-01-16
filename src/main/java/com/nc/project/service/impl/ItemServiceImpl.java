package com.nc.project.service.impl;

import com.nc.project.entity.Item;
import com.nc.project.repository.ItemRepository;
import com.nc.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    public final ItemRepository itemRepository;
    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public List<Item> getItemList() {
        return null;
    }
}
