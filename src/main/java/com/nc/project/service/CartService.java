package com.nc.project.service;

import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.UserAccount;

import java.util.List;
import java.util.Map;


public interface CartService {

    // 상품 상세페이지에서 장바구니 추가 기능
    public void addCart(UserAccount userAccount, Long itemId);

    // 유저의 장바구니 상품 목록을 찾아주는 기능
    public List<CartItem> getCartItem (long id);

    // 장바구니의 상품목록의 상품 개수를 버튼으로 바꿔주는 기능 (완료)
    public Map<String, Integer> updateCartItemCount (Long cartItemId, String action, UserAccount userAccount);

    // 장바구니에서 목록의 장바구니 아이템을 X버튼으로 삭제하는 기능
    public void deleteCartItem (long cartItemId);

    // 유저의 장바구니 객체를 가져오는 기능
    public Cart getCart (long id);
}
