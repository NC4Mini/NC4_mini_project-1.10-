package com.nc.project.controller;

import com.nc.project.entity.Item;
import com.nc.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

        mav.setViewName("item/add_item.html");

        return mav;
    }

    // 메인에서 상품 상세페이지 이동 (임시작업)
    @GetMapping("/item-detail")
    public ModelAndView getItemDetail (@RequestParam Long itemId) {
        Item item = itemService.getItem(itemId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("item", item);
        mav.setViewName("item/item_detail_test.html");

        return mav;
    }
}
