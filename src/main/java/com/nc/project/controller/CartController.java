package com.nc.project.controller;

import com.nc.project.dto.CartItemDTO;
import com.nc.project.entity.UserDetail;
import com.nc.project.service.CartService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 장바구니 페이지 이동
    @GetMapping("/mycart")
    public ModelAndView getCartList () {
        ModelAndView mav = new ModelAndView();


        mav.setViewName("cart/getCart.html");

        return mav;
    }

    // 장바구니 페이지에서 배송지 변경 이동
    @GetMapping("/change-addr")
    public ModelAndView changeAddr() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("cart/changeAddr.html");

        return mav;
    }

    // 장바구니에 물건 추가
//    @PostMapping("/add/{id}/{itemId}")
//    public String addCartItem (@PathVariable("id") Integer id, @PathVariable("itemId") Integer itemId, int itemCount) {
//        UserDetail userDetail =
//    }


}
