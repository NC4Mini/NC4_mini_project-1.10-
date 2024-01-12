package com.nc.project.service;

import com.nc.project.dto.ItemDTO;

public interface CartItemService {
    // cartitem에 item 담는 메서드
    void addCartItem (ItemDTO itemDTO);
}
