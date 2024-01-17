package com.nc.project.service;

import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.UserAccountDTO;


public interface CartService {

    // 상품 상세페이지에서 수량 정해서 장바구니 넣어주는 기능
    public void addCart (UserAccountDTO userAccountDTO, ItemDTO itemDTO, int itemCnt);
}
