package com.nc.project.service;

import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.Cart;


public interface CartService {

    // 상품 상세페이지에서 수량 정해서 장바구니 넣어주는 기능
    public void addCart (UserAccountDTO userAccountDTO, ItemDTO itemDTO, int itemCnt);

//    // 유저의 장바구니를 찾아주는 기능
//    public Cart getCart (long id);

    // 장바구니에서 장바구니 아이템을 삭제하는 기능
    public void deleteCartItem (long cartItemId);
}
