package com.nc.project.service;

public interface OrdersService {
    
    // 장바구니에서 주문하기 기능
    public void orderFromCart (long id, long cartId, long shpAddrId);
    
}