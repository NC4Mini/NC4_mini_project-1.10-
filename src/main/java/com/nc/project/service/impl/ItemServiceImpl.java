package com.nc.project.service.impl;

import com.nc.project.dto.ItemDTO;
import com.nc.project.entity.Item;
import com.nc.project.entity.ItemFile;
import com.nc.project.repository.ItemRepository;
import com.nc.project.service.ItemService;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

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
    public void modifyItem(ItemDTO itemDTO) {
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println(itemDTO);
        Item updateItem = itemDTO.toEntity();

        List<ItemFile> itemFileList = itemDTO.getItemFileDTOList().stream()
                .map(itemFileDTO -> itemFileDTO.toEntity(updateItem)).toList();

        itemFileList.forEach(
                updateItem::addItemFileList
        );

        itemRepository.save(updateItem);

    }

    @Override
    public void deleteItem(long itemId) {
        Item item = itemRepository.findById(itemId).get();
        itemRepository.delete(item);
    }

    @Override
    public Item getItem(long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Override
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

    @Override
    public Page<Item> ItemSearchList(String searchKeyword, Pageable pageable) {
        return itemRepository.findByItemNameContaining(searchKeyword, pageable);
    }

    @Override
    public Page<Item> ItemListRandom(Pageable pageable) {
        return itemRepository.findAllOrderByRandom(pageable);
    }

    @Override
    public Page<Item> ItemList(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

}
