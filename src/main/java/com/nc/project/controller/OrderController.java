package com.nc.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    // 주문서 페이지로 이동
    @GetMapping("/myorder")
    public ModelAndView getOrder() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("/order/getOrder.html");

        return mav;
    }
}
