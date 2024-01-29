

package com.nc.project.service.impl;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.Delivery;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.CartRepository;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.DeliveryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    
    public final CartRepository cartRepository;
    public final UserAccountRepository userAccountRepository;

    // 장바구니에서 주문하기 기능
    @Override
    public Delivery deliveryFromCart(long id, long cartId) {
        Delivery delivery = new Delivery();

        UserAccount userAccount = userAccountRepository.findById(id).get();
        
        Cart cart = cartRepository.findById(cartId).get();
        
        List<CartItem> cartItemList = cart.getCartItemList();
        
        delivery.setUserAccount(userAccount);
        delivery.setDeliveryItemList(cartItemList);
        delivery.setTotalPrice(cart.getTotalPrice());
        delivery.setDeliveryTime(java.time.LocalDateTime.now());
        delivery.setDeliveryStatus(0);


        return delivery;

    }

    
    

    
        
        
    
}