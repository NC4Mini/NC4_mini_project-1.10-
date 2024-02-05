package com.nc.project.controller;

import com.nc.project.entity.Item;
import com.nc.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class MainController {


    private final ItemService itemService;

    @GetMapping
    public String searchMain(@RequestParam(name="search", required = false) String search,
                             @PageableDefault(page = 0, size = 4) Pageable pageable,
                             Model model) {

        Page<Item> pageList = null;

        pageList = itemService.ItemListRandom(pageable);

        model.addAttribute("itemList", pageList);
        model.addAttribute("search", search);
        return "index";
    }
}
