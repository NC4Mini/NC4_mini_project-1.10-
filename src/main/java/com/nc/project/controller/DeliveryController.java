package com.nc.project.controller;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.nc.project.entity.Cart;
import com.nc.project.entity.Delivery;
import com.nc.project.entity.UserAccount;
import com.nc.project.entity.UserShpAddr;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.service.CartService;
import com.nc.project.service.DeliveryService;
import com.nc.project.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
@Slf4j
public class DeliveryController {

    public final DeliveryService deliveryService;
    public final UserService userService;
    public final CartService cartService;
    
    // 장바구니에서 주문하기 기능
    @PostMapping("/to-delivery")
    public ModelAndView getDelivery (Principal principal, @RequestParam ("cartId") long cartId ) {
        ModelAndView mav = new ModelAndView();

        // 로그인 하지 않은 경우
        if (principal == null) {
            mav.setViewName("redirect:/user/login");
            return mav;
        }
        String userName = principal.getName();

        UserAccount userAccount = userService.findUser(userName);

        Cart cart = cartService.getCart(userAccount.getId());

        UserShpAddr defaultUserShpAddr = cartService.bringDefaultAddr(userAccount.getId());

        Delivery delivery = Delivery.builder()
            .userAccount(userAccount)
            .totalPrice(cart.getTotalPrice())
            .deliveryStatus(0)
            .build();

        mav.addObject("delivery", delivery);
        mav.addObject("cart", cart);
        mav.addObject("defaultUserShpAddr", defaultUserShpAddr);
        mav.setViewName("delivery/get_delivery.html");

        return mav;
    }

    // 결제하기 기능 (일반 결제)
    @PostMapping("/confirm-delivery")
    @Transactional
    public ModelAndView confirmDelivery (@RequestParam ("cartId") String toCartId){
        ModelAndView mav = new ModelAndView();

        long cartId = Long.parseLong(toCartId);

        // 사용자 id를 받아 새로운 상태의 delivery를 저장하는 메서드
        deliveryService.confirmDelivery(cartId);

        mav.setViewName("delivery/complete_delivery.html");

        // 완료 페이지로 이동
        return mav;
    }

    // 결제하기 기능 (토스 결제)
    @GetMapping("/success")
    @Transactional
    public ModelAndView successTossPay (Principal principal, @RequestParam ("paymentKey") String paymentKey,
    @RequestParam ("orderId") String deliveryId, @RequestParam ("amount") int amount) {
        ModelAndView mav = new ModelAndView();
        

        UserAccount userAccount = userService.findUser(principal.getName());

        Cart cart = cartService.getCart(userAccount.getId());

        // 사용자 id를 받아 새로운 상태의 delivery를 저장하는 메서드
        deliveryService.successTossPay(cart, userAccount, deliveryId, amount);

        mav.setViewName("delivery/complete_delivery.html");

        // 완료 페이지로 이동
        return mav;
    }

}
