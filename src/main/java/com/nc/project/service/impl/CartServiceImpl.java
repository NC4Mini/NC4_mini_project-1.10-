package com.nc.project.service.impl;//package com.example.tempproject.service.impl;

import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.Item;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.*;
import com.nc.project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final UserAccountRepository userAccountRepository;


    // 장바구니에 물건 담기
    @Override
    @Transactional
    public void addCart(UserAccountDTO userAccountDTO, ItemDTO itemDTO, int itemCnt) {

        // 1. body에 담겨있는 userId로 유저의 장바구니가 있는지 확인

        // 받아온 DTO 객체 entity로 변환
        UserAccount userAccount = userAccountDTO.toEntity();

        // 유저 고유 id로 유저의 장바구니 찾기
        Cart userCart = cartRepository.findByUserAccountId(userAccount.getId());

        // 장바구니 없을 시 받아온 유저의 장바구니 생성
        if (userCart == null) {
            userCart = Cart.createNewCart(userAccount);
            cartRepository.save(userCart);
        }

        // 2. 장바구니에 받아온 상품 객체 추가

        // 받아온 DTO 객체에 해당하는 item entity를 가져옴
        Item item = itemRepository.getReferenceById(itemDTO.getItemId());

        // 장바구니 상품 entity를 찾음
        CartItem cartItem = cartItemRepository.findCartItemByCart_CartIdAndItem_ItemId(userCart.getCartId(), item.getItemId());

        // 상품이 장바구니에 없을 때 카트 상품 생성 후 추가
        if (cartItem == null) {
            cartItem = CartItem.createCartItem(userCart, item, itemCnt);
            cartItemRepository.save(cartItem);
        }
        // 이미 있다면 수량만 증가
        else {
            cartItem.setCartItemCnt(itemCnt);
        }


//        // 상품이 장바구니에 없을 때 카트 상품 생성 후 추가
//        if (cartItem == null) {
//            cartItem = CartItem.createCartItem(cart, item, itemCount);
//            cartItemRepository.save(cartItem);
//        }

        // 상품이 장바구니에 이미 있을 때 수량만 증가
//        else {
//            CartItem updateCartItem = cartItem;
//            updateCartItem.setCart(cartItem.getCart());
//            updateCartItem.setItem(cartItem.getItem());
//            updateCartItem.addCount(itemCount);
//            cartItemRepository.save(updateCartItem);
//        }


    }
}