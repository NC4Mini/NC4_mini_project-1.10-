package com.nc.project.controller;

import com.nc.project.repository.UserDetailRepository;
import com.nc.project.service.CartService;
import com.nc.project.service.ItemService;
import com.nc.project.service.UserService;
import com.nc.project.service.impl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;
    private final UserDetailRepository userDetailRepository;
    private final CartServiceImpl cartServiceImpl;
    private final UserService userService;

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
//    public String addCartItem (@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, int itemCount) {
//        UserDetail userDetail = userService.getUser(id);
//        Item item = itemService.getItem(itemId);
//
//        cartService.addCart(userDetail, item, itemCount);
//    }


}
