package com.nc.project.controller;

import com.nc.project.entity.Item;
import com.nc.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/search")
@Controller
public class SearchController {

    private final ItemService itemService;

    @GetMapping
    public String searchMain(@RequestParam(name="search", required = false) String search,
                             @PageableDefault(page = 0, size = 12, sort = "itemId", direction = Sort.Direction.DESC) Pageable pageable,
                            Model model) {

        Page<Item> pageList = null;
        pageList = itemService.ItemList(pageable);
        model.addAttribute("itemList", pageList);

        if(search == null || search.isBlank()) {
            pageList = itemService.ItemList(pageable);
            model.addAttribute("itemList", pageList);
        } else {
            pageList = itemService.ItemSearchList(search, pageable);
            model.addAttribute("itemList", pageList);
        }

        model.addAttribute("search", search);
        return "search/search";
    }



}
