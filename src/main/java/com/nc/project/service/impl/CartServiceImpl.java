package com.nc.project.service.impl;//package com.example.tempproject.service.impl;

import com.nc.project.dto.CartDTO;
import com.nc.project.dto.CartItemDTO;
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

import java.util.*;

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

    }

    // 해당 사용자의 장바구니를 표출해주는 기능 (완료)
    @Override
    public List<CartItem> getCartItem (long id) {
        // 받아온 id로 cart를 찾기
        Cart cart = cartRepository.findByUserAccountId(id);
        // 해당하는 모든 CartItem을 찾기위해 CartItem타입 리스트 선언
        List<CartItem> cartItemList = new ArrayList<>();
        // 찾은 cart의 cart_id에 해당하는 모든 CartItem들을 불러오기
        cartItemList = cartItemRepository.findAllByCart_CartId(cart.getCartId());

        return cartItemList;

//        DTO형태로 리턴
//        // 불러온 CartItem들을 DTO로 다시 변환해주는 작업
//        List<CartItemDTO> cartItemDTOList = cartItemList.stream().map(cartItem -> cartItem.toDTO()).toList();
//        // DTO로 바꾼 CartItem들을 반환하기
//        return cartItemDTOList;
    }

    // 장바구니의 상품목록의 상품 개수를 버튼으로 바꿔주는 기능 (완료)
    @Override
    public Map<String, Integer> updateCartItemCount(Long cartItemId, String action) {
        Map<String, Integer> newCartItemMap = new HashMap<>();

        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        Item item = cartItemRepository.findById(cartItemId).get().getItem();

        if ("increase".equals(action)) {
            cartItem.setCartItemCnt(cartItem.getCartItemCnt() + 1);
        } else if ("decrease".equals(action)) {
            cartItem.setCartItemCnt(cartItem.getCartItemCnt() - 1);
        }

        cartItemRepository.save(cartItem);

        newCartItemMap.put("newCartItemCnt", cartItem.getCartItemCnt());
        newCartItemMap.put("newItemPrice", item.getItemPrice());

        return newCartItemMap;
    }

    // 장바구니의 상품을 지우는 기능 (완료)
    @Override
    public void deleteCartItem(long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();

        cartItemRepository.delete(cartItem);
    }

}