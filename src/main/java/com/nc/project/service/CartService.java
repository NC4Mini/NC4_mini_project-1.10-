package com.nc.project.service;

import com.nc.project.dto.CartItemDTO;
import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.UserDetailDTO;
import com.nc.project.entity.Item;
import com.nc.project.entity.UserDetail;

public interface CartService {

    // user의 id와 cartItem을 받아서 장바구니에 추가
    public void addCart (UserDetailDTO userDetailDTO, ItemDTO itemDTO);
}
