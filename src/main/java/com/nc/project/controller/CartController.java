package com.nc.project.controller;

import com.nc.project.dto.CartItemDTO;
import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.ResponseDTO;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.entity.CartItem;
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
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;
    private final UserService userService;
    private final UserAccountRepository userAccountRepository;
    private final ItemRepository itemRepository;

    private final Logger logger = LoggerFactory.getLogger(BoardController.class);

    // 해당 유저의 장바구니 페이지 이동
    @GetMapping("/mycart")
    public ModelAndView getUserCart (Principal principal) {
        ModelAndView mav = new ModelAndView();

        // 사용자 정보가 없을 경우 로그인창으로 이동
        if (principal == null) {
            RedirectView redirectView = new RedirectView("/login");

            mav.setView(redirectView);

            return mav;
        }

        String userName = principal.getName();

        long id = userAccountRepository.findByUserId(userName).get().getId();

        mav.addObject("cart", cartService.getCart(id));
        mav.addObject("cartItemList", cartService.getCartItem(id));

        mav.setViewName("cart/get_cart_test.html");

        return mav;
    }

     // 장바구니 페이지에서 상품 수량 변경하는 기능 (완료, 01.19)
    @PostMapping("/update-itemCnt")
    public ResponseEntity<?> updateCartItemCnt (Long cartItemId, String action) {

        Map<String, Integer> response = new HashMap<>();

        response = cartService.updateCartItemCount(cartItemId, action);

        return ResponseEntity.ok(response);
    }
    // 장바구니 페이지에서 상품목록 삭제하는 기능
    @DeleteMapping("/delete-cart-item")
    public ResponseEntity<?> deleteCartItem (Long cartItemId) {
        ResponseDTO<Map<String, String>> response = new ResponseDTO<>();
        Map<String, String> returnMap = new HashMap<>();

        cartService.deleteCartItem(cartItemId);

        returnMap.put("msg", "삭제 되었습니다.");

        response.setItem(returnMap);
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }


    // 장바구니 페이지에서 배송지 변경 이동
    @GetMapping("/addr-select")
    public ModelAndView changeAddr() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("cart/addr_select.html");

        return mav;
    }

    // 배송지 변경 페이지에서 배송지 수정 페이지 이동
    @GetMapping("/addr-modify")
    public ModelAndView modifyAddr () {
        ModelAndView mav = new ModelAndView();


        mav.setViewName("cart/addr_modify.html");

        return mav;
    }

    // 배송지 변경 페이지에서 새 배송지 추가 페이지 이동
    @GetMapping("/addr-add")
    public ModelAndView addAddr() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("cart/addr_Add.html");

        return mav;
    }

    // 상품 상세페이지에서 장바구니에 물건 추가
    @PostMapping("/add/{itemId}")
    public ResponseEntity<?> addCartItem (Principal principal,@PathVariable Long itemId) {
        // 사용자 정보가 없을 경우 로그인창으로 이동
        if (principal == null) {
            String loginUrl = "/login";
            return ResponseEntity.status(HttpStatus.FOUND).header("Add Fail", loginUrl).build();
        }

        UserAccount userAccount = userAccountRepository.findByUserId(principal.getName()).get();

        cartService.addCart(userAccount, itemId);

        String itemUrl = "/item/item-detail?itemId=" + itemId;

        // 다시 해당 페이지로 리다이렉트
//        return ResponseEntity.status(HttpStatus.OK).header("Add Complete", itemUrl).build();
        return ResponseEntity.ok("장바구니에 추가되었습니다.");

    }


}
