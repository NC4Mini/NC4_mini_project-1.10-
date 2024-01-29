

package com.nc.project.service.impl;

import org.springframework.stereotype.Service;

import com.nc.project.entity.Cart;
import com.nc.project.entity.CartItem;
import com.nc.project.entity.Delivery;
import com.nc.project.repository.CartRepository;
import com.nc.project.service.DeliveryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    
    public final CartRepository cartRepository;

    @Override
    public Delivery deliveryFromCart(long id, long cartId, long shpAddrId) {
        Delivery delivery = new Delivery();

        Cart cart = cartRepository.findById(cartId).get();

        return delivery;

    }

    
    

    
        
        
    
}