package com.nc.project.service.impl;

import com.nc.project.dto.ItemDTO;
import com.nc.project.entity.Cart;
import com.nc.project.entity.Item;
import com.nc.project.repository.CartItemRepository;
import com.nc.project.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    // db - CART_ITEM에 페이지의 ITEM 객체를 담아주는 기능
    @Override
    public void addCartItem(ItemDTO itemDTO) {
        Item item = itemDTO.toEntity();

//        cartItemRepository.save(item);
    }
}
