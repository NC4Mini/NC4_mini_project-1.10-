package com.nc.project.service.impl;//package com.example.tempproject.service.impl;

import com.nc.project.dto.CartItemDTO;
import com.nc.project.dto.ItemDTO;
import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.Item;
import com.nc.project.entity.UserDetail;
import com.nc.project.repository.CartItemRepository;
import com.nc.project.repository.CartRepository;
import com.nc.project.repository.ItemRepository;
import com.nc.project.repository.UserDetailRepository;
import com.nc.project.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final UserDetailRepository userDetailRepository;


    // 장바구니에 물건 담기
    @Override
    @Transactional
    public void addCart(UserDetail userDetail, Item addItem, int itemCount) {

        // 유저 고유 id로 유저의 장바구니 찾기
        Cart cart = cartRepository.findByUserDetailId(userDetail.getId());

        // 장바구니 없을 시
        if (cart == null) {
            cart = Cart.createCart(userDetail);
            cartRepository.save(cart);
        }

        Item item = itemRepository.getReferenceById(addItem.getItemId());
        CartItem cartItem = cartItemRepository.findCartItemByCart_CartIdAndItem_ItemId(cart.getCartId(), item.getItemId());

        // 상품이 장바구니에 없을 때 카트 상품 생성 후 추가
        if (cartItem == null) {
            cartItem = CartItem.createCartItem(cart, item, itemCount);
            cartItemRepository.save(cartItem);
        }

        // 상품이 장바구니에 이미 있을 때 수량만 증가
        else {
            CartItem updateCartItem = cartItem;
            updateCartItem.setCart(cartItem.getCart());
            updateCartItem.setItem(cartItem.getItem());
            updateCartItem.addCount(itemCount);
            cartItemRepository.save(updateCartItem);
        }

    }
}