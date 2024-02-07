package com.nc.project.service;

import com.nc.project.entity.Cart;
import com.nc.project.entity.Delivery;
import com.nc.project.entity.UserAccount;

public interface DeliveryService {
    
    // 결제하기 기능
    public void confirmDelivery(long cartId);

    // // 주문 찾기 기능
    // public Delivery findDelivery(long deliveryId);

    // 토스 결제하기 기능
    public void successTossPay(Cart cart, UserAccount userAccount, String deliveryId, int amount);
}