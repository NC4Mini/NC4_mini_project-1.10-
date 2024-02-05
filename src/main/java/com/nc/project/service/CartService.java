package com.nc.project.service;

import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.UserAccount;
import com.nc.project.entity.UserShpAddr;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Map;


public interface CartService {

    // 상품 상세페이지에서 장바구니 추가 기능
    public void addCart(UserAccount userAccount, long itemId);

    // 해당 장바구니를 찾아주는 기능
    public Cart getCart (Long cartId);

    // 유저의 장바구니 상품 목록을 찾아주는 기능
    public List<CartItem> getCartItem (long id);

    // 장바구니의 상품목록의 상품 개수를 버튼으로 바꿔주는 기능 (완료)
    public Map<String, Integer> updateCartItemCount (Long cartItemId, String action, UserAccount userAccount);

    // 장바구니에서 목록의 장바구니 아이템을 X버튼으로 삭제하는 기능
    public Cart deleteCartItem (long cartId, long cartItemId);

    // 유저의 장바구니 객체를 가져오는 기능
    public Cart getCart (long id);

    // 배송지 목록을 가져오는 기능
    public List<UserShpAddr> bringUserShpAddrList (long id);

    // 기본 배송지 객체를 가져오는 기능
    public UserShpAddr bringDefaultAddr (long id);

    // 배송지를 수정해주는 기능
    public void updateShpAddr (long id, int shpAddrId);

    // 배송지를 추가해주는 기능
    public void addShpAddr (long id, Map<String, String> userShpAddrMap);

//    // 장바구니번호로 유저정보를 가져오는 기능
//    public UserAccount getUserAccountByCartId (long cartId);

    // 장바구니의 총액을 업데이트 해주는 기능
    public int calcTotalPrice (long cartId);
}
