package com.nc.project.repository;

import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // 장바구니 아이디 (pk)와 상품아이디를 이용해서 상품이 장바구니에 들어있는지 조회.
    CartItem findCartItemByCart_CartIdAndItem_ItemId (Long cartId, Long itemId);

    CartItem findCartItemByItem_ItemId (Long itemId);
    List<CartItem> findAllByCart_CartId (Long cartId);

}
