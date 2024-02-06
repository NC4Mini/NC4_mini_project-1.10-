package com.nc.project.controller;

import com.nc.project.dto.CartItemDTO;
import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.ResponseDTO;
import com.nc.project.dto.UserAccountDTO;
import com.nc.project.dto.UserShpAddrDTO;
import com.nc.project.entity.*;
import com.nc.project.repository.CartRepository;
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
import org.springframework.transaction.annotation.Transactional;
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
    private final CartRepository cartRepository;

    private final Logger logger = LoggerFactory.getLogger(BoardController.class);

    // 해당 유저의 장바구니 페이지 이동
    @Transactional
    @GetMapping("/mycart")
    public ModelAndView getUserCart (Principal principal) {
        ModelAndView mav = new ModelAndView();

        // 사용자 정보가 없을 경우 로그인창으로 이동
        if (principal == null) {
            RedirectView redirectView = new RedirectView("/user/login");

            mav.setView(redirectView);

            return mav;
        }

        String userId = principal.getName();

        UserAccount userAccount = userService.findUser(userId);

        long id = userAccount.getId();

        UserShpAddr userDefaultShpAddr = cartService.bringDefaultAddr(id);

        mav.addObject("defaultAddr", userDefaultShpAddr);
        mav.addObject("cart", cartService.getCart(id));
        mav.addObject("cartItemList", cartService.getCartItem(id));

        mav.setViewName("cart/get_cart.html");

        return mav;
    }

    // 장바구니 페이지에서 상품 수량 변경하는 기능 (완료, 01.19)
    @PostMapping("/update-itemCnt")
    public ResponseEntity<?> updateCartItemCnt (@RequestParam("cartItemId") Long cartItemId, @RequestParam("action") String action, Principal principal) {

        Map<String, Integer> response = new HashMap<>();

        UserAccount userAccount = userService.findUser(principal.getName());

        response = cartService.updateCartItemCount(cartItemId, action, userAccount);

        return ResponseEntity.ok(response);
    }

    // 장바구니 페이지에서 상품목록 삭제하는 기능
    @DeleteMapping("/delete-cart-item")
    public ResponseEntity<?> deleteCartItem (@RequestParam ("cartItemId") Long cartItemId, @RequestParam("cartId") Long cartId) {
        Map<String, String> response = new HashMap<>();

        Cart cart = cartService.deleteCartItem(cartId, cartItemId);

        response.put("msg", "삭제 되었습니다.");
        response.put("newTotalPrice", String.valueOf(cart.getTotalPrice()));

        return ResponseEntity.ok(response);
    }


    // 장바구니 페이지에서 배송지 변경 페이지 이동
    @GetMapping("/addr-select")
    public ModelAndView changeAddr(Principal principal) {
        ModelAndView mav = new ModelAndView();

        UserAccount userAccount = userService.findUser(principal.getName());

        long id = userAccount.getId();

        List<UserShpAddr> userShpAddrList = cartService.bringUserShpAddrList(id);

        mav.addObject("userShpAddrList", userShpAddrList);
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

        mav.setViewName("cart/addr_add.html");

        return mav;
    }

    // 상품 상세페이지에서 장바구니에 물건 추가
    @PostMapping("/add/{itemId}")
    public ResponseEntity<?> addCartItem(Principal principal, @RequestParam("itemId") long itemId) {
        Map<String, String> response = new HashMap<>();

        // 사용자 정보가 없을 경우 로그인창으로 이동
        if (principal == null) {
            
            response.put("msg", "로그인이 필요합니다.");

            // 응답코드 401 띄우고 메세지 보내기
            return ResponseEntity.status(401).body(response);
        }
        
        // 없으면 null 값 까지도 가져옴
        UserAccount userAccount = userAccountRepository.findByUserId(principal.getName()).orElse(null);

        cartService.addCart(userAccount, itemId);

        response.put("msg", "장바구니에 담겼습니다.");

        return ResponseEntity.ok(response);
    }

    // 배송지 선택 페이지에서 기본 배송지 변경
    @PostMapping("/addr-select")
    public ResponseEntity<?> changeDefaultAddr (@RequestBody Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        long id = Long.parseLong(data.get("userId"));
        int shpAddrId = Integer.parseInt(data.get("addrId"));
        
        System.out.println("=============================");
        System.out.println(id);
        System.out.println(shpAddrId);


        try {
            cartService.updateShpAddr(id, shpAddrId);
            response.put("msg", "변경되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("msg", "변경에 실패했습니다.");
            return ResponseEntity.status(500).body(response);
        }
    }

    // 배송지 추가 페이지에서 배송지 추가
    @PostMapping("/addr-add")
    public ResponseEntity<?> addShpAddr (Principal principal, @RequestBody Map<String, String> data) {
        Map<String, String> response = new HashMap<>();

        try {
            UserAccount userAccount = userService.findUser(principal.getName());

            long id = userAccount.getId();

            cartService.addShpAddr(id, data);
            
            response.put("success", "추가되었습니다.");
            return ResponseEntity.ok(response);

        }
        catch (Exception e) {
            response.put("fail", "추가에 실패했습니다.");
            return ResponseEntity.status(500).body(response);
        }
    }
}
