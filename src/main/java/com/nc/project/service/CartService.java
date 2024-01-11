package com.nc.project.service;

import com.nc.project.dto.ItemDTO;
public interface CartService {

    // 장바구니에 추가
    void addCart (ItemDTO itemDTO);
}
