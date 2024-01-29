package com.nc.project.controller;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nc.project.entity.Delivery;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.CartService;
import com.nc.project.service.DeliveryService;
import com.nc.project.service.UserService;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    public final DeliveryService deliveryService;
    public final UserService userService;
    public final CartService cartService;

    // 장바구니에서 주문하기 기능
    @PostMapping("/to-delivery")
    public ModelAndView getDelivery(@RequestParam ("cartId") long cartId) {
        ModelAndView mav = new ModelAndView();

        long id = cartService.getUserAccountByCartId(cartId).getId();

        Delivery delivery = new Delivery();
        delivery = deliveryService.deliveryFromCart(id, cartId);

        mav.addObject("delivery", delivery);
        mav.setViewName("delivery/get_delivery.html");

        return mav;
    }
}
