package com.nc.project.controller;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
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
public class DeliveryController {

    public final DeliveryService deliveryService;
    public final UserService userService;
    public final CartService cartService;

    // // 장바구니에서 주문하기 기능
    // @PostMapping("/to-delivery")
    // public ModelAndView getDelivery(Principal Principal, @RequestParam ("cartId") long cartId) {
    //     ModelAndView mav = new ModelAndView();

    //     // 로그인 하지 않은 경우
    //     if (Principal == null) {
    //         mav.setViewName("redirect:/login");
    //         return mav;
    //     }

    //     long id = cartService.getUserAccountByCartId(cartId).getId();

    //     Delivery delivery = deliveryService.deliveryFromCart(id, cartId);
    //     // tempDelivery = deliveryService.deliveryFromCart(id, cartId);
        
    //     UserShpAddr defaultUserShpAddr = cartService.bringDefaultAddr(id);
        
    //     mav.addObject("delivery", delivery);
    //     mav.addObject("defaultUserShpAddr", defaultUserShpAddr);
    //     mav.setViewName("delivery/get_delivery.html");

    //     return mav;
    // }

    // @PostMapping("/confirm-delivery")
    // @Transactional
    // public ModelAndView confirmDelivery (@RequestParam ("userAccountId") long id) {
    //     ModelAndView mav = new ModelAndView();

    //     // 사용자 id를 받아 새로운 상태의 delivery를 저장하는 메서드
    //     deliveryService.confirmDelivery(id);

    //     mav.setViewName("delivery/complete_delivery.html");

    //     // 완료 페이지로 이동
    //     return mav;
    // }
    
    // 장바구니에서 주문하기 기능
    @PostMapping("/to-delivery")
    public ModelAndView getDelivery (Principal Principal, @RequestParam ("cartId") long cartId) {
        ModelAndView mav = new ModelAndView();

        // 로그인 하지 않은 경우
        if (Principal == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }

        Cart cart = cartService.getCart(cartId);

        UserAccount userAccount = cartService.getUserAccountByCartId(cartId);

        UserShpAddr defaultUserShpAddr = cartService.bringDefaultAddr(userAccount.getId());

        mav.addObject("cart", cart);
        mav.addObject("defaultUserShpAddr", defaultUserShpAddr);
        mav.setViewName("delivery/get_delivery.html");

        return mav;
    }

    // 결제하기 기능
    @PostMapping("/confirm-delivery")
    @Transactional
    public ModelAndView confirmDelivery (@RequestParam ("cartId") long cartId) {
        ModelAndView mav = new ModelAndView();

        // 사용자 id를 받아 새로운 상태의 delivery를 저장하는 메서드
        deliveryService.confirmDelivery(cartId);

        mav.setViewName("delivery/complete_delivery.html");

        // 완료 페이지로 이동
        return mav;
    }
}
