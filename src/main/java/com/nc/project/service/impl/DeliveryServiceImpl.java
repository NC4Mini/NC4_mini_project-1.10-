

package com.nc.project.service.impl;

import org.springframework.stereotype.Service;

import com.nc.project.entity.Cart;
import com.nc.project.repository.CartRepository;
import com.nc.project.service.DeliveryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    
    public final CartRepository cartRepository;

    @Override
    public void deliveryFromCart(long id, long cartId, long shpAddrId) {
        
        
        
    }
    

    
        
        
    
}