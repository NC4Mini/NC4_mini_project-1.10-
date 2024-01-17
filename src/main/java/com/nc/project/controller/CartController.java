package com.nc.project.controller;

import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.Item;
import com.nc.project.entity.UserAccount;
import com.nc.project.repository.ItemRepository;
import com.nc.project.repository.UserAccountRepository;
import com.nc.project.repository.UserDetailRepository;
import com.nc.project.service.CartService;
import com.nc.project.service.ItemService;
import com.nc.project.service.UserService;
import com.nc.project.service.impl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;
    private final UserService userService;

    // 장바구니 임시 접속용
    @GetMapping("/mycart")
    public ModelAndView getUserCart () {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("cart/getCart.html");

        return mav;
    }

    // 유저의 해당 장바구니 페이지 이동
    @GetMapping("/mycart/{id}")
    public ModelAndView getUserCart (@PathVariable("id") long id) {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("cart/getCart.html");

        return mav;
    }

    // 장바구니 페이지에서 배송지 변경 이동
    @GetMapping("/addr-select")
    public ModelAndView changeAddr() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("cart/addrSelect.html");

        return mav;
    }

    // 배송지 변경 페이지에서 배송지 수정 페이지 이동
    @GetMapping("/addr-modify")
    public ModelAndView modifyAddr () {
        ModelAndView mav = new ModelAndView();


        mav.setViewName("cart/addrModify.html");

        return mav;
    }

    // 배송지 변경 페이지에서 새 배송지 추가 페이지 이동
    @GetMapping("/addr-add")
    public ModelAndView addAddr() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("cart/addrAdd.html");

        return mav;
    }

    // 상품 상세페이지에서 장바구니에 물건 추가 (개수 지정)
    @PostMapping("/add/{id}/{itemId}/{itemCount}")
    public void addCartItem () {

    }


}
