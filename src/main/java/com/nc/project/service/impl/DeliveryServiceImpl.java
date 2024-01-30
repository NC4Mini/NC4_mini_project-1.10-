

package com.nc.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.Delivery;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.CartRepository;
import com.nc.project.repository.DeliveryRepository;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.DeliveryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    
    public final CartRepository cartRepository;
    public final UserAccountRepository userAccountRepository;
    public final DeliveryRepository deliveryRepository;

    // 결제하기 기능
    public void confirmDelivery(long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        UserAccount userAccount = cart.getUserAccount();
        List<CartItem> cartItemList = new ArrayList<>(cart.getCartItemList());
        double totalPrice = cart.getTotalPrice();
        Delivery delivery = Delivery.builder()
            .userAccount(userAccount)
            .deliveryItemList(cartItemList)
            .totalPrice(totalPrice)
            .deliveryStatus(1)
            .build();
        deliveryRepository.save(delivery);

        // 장바구니 비우기
        cartRepository.delete(cart);
    }

    

}