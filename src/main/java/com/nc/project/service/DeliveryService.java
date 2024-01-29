package com.nc.project.service;

public interface DeliveryService {
    
    // 장바구니에서 주문하기 기능
    public void deliveryFromCart(long id, long cartId, long shpAddrId);
    
}