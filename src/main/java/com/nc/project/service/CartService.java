package com.nc.project.service;

import com.nc.project.dto.CartItemDTO;
import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;

import java.util.List;


public interface CartService {

    // 상품 상세페이지에서 수량 정해서 장바구니 넣어주는 기능
    public void addCart (UserAccountDTO userAccountDTO, ItemDTO itemDTO, int itemCnt);

    // 유저의 장바구니 상품 목록을 찾아주는 기능
    public List<CartItem> getCartItem (long id);

    // 장바구니의 상품목록의 상품 개수를 버튼으로 바꿔주는 기능 (완료)
    public CartItem updateCartItemCount (Long cartItemId, String action);

    // 장바구니에서 목록의 장바구니 아이템을 X버튼으로 삭제하는 기능
    public void deleteCartItem (long cartItemId);
}
