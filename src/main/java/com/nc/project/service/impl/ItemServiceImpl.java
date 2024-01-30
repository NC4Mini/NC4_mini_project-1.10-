package com.nc.project.service.impl;

import com.nc.project.dto.ItemDTO;
import com.nc.project.entity.Item;
import com.nc.project.entity.ItemFile;
import com.nc.project.repository.ItemRepository;
import com.nc.project.service.ItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    public final ItemRepository itemRepository;

    @Transactional
    @Override
    public void insertItem(ItemDTO itemDTO) {

        Item item = itemDTO.toEntity();

        List<ItemFile> itemFileList = itemDTO.getItemFileDTOList().stream()
                        .map(itemFileDTO -> itemFileDTO.toEntity(item)).toList();

        itemFileList.forEach(
                item::addItemFileList
        );

        itemRepository.save(item);
    }

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item getItem(long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Override
    public List<Item> getItemList() {
        return null;
    }
}
