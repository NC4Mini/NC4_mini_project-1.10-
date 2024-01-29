package com.nc.project.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    // 주문서 페이지로 이동
    @GetMapping("/new-delivery")
    public ModelAndView getDelivery(Principal principal) {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("/delivery/get_delivery.html");

        return mav;
    }
}
