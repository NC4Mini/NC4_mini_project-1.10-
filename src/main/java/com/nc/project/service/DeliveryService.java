package com.nc.project.service;

import com.nc.project.entity.Delivery;

public interface DeliveryService {
    
    // 결제하기 기능
    public void confirmDelivery(long cartId);

    // // 주문 찾기 기능
    // public Delivery findDelivery(long deliveryId);

}