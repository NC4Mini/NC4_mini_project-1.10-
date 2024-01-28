

package com.nc.project.service.impl;

import org.springframework.stereotype.Service;

import com.nc.project.entity.Cart;
import com.nc.project.repository.CartRepository;
import com.nc.project.service.OrdersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService{

    
    public final CartRepository cartRepository;
    

    @Override
    public void orderFromCart(long id, long cartId, long shpAddrId) {
        
        // 1. 유저의 장바구니를 가져옴
        Cart userCart = cartRepository.findByUserAccountId(id);


        
    }
        
        
    
}