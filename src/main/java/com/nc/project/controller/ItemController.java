package com.nc.project.controller;

import com.nc.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // 상품 등록 페이지 이동
    @GetMapping("/add-item")
    public ModelAndView addItem() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("item/addItem.html");

        return mav;
    }

    // 메인에서 상품 상세페이지 이동 (임시작업)
    @GetMapping("/item-detail")
    public ModelAndView getItemDetail () {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("item/itemDetail.html");

        return mav;
    }
}
