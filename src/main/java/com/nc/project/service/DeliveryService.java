package com.nc.project.service;

import com.nc.project.entity.Delivery;

public interface DeliveryService {
    
    // 장바구니에서 주문하기 기능
    public Delivery deliveryFromCart(long id, long cartId);
    
}