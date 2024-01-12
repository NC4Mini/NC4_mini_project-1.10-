package com.nc.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    //1대 1 문의 페이지 이동
    @GetMapping("/board-list")
    public ModelAndView board(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("board/getBoard.html");

        return mav;
    }

    // 1대 1 문의 글쓰기 페이지 이동
    @GetMapping("/getBoardList")
    public ModelAndView getBoardList () {
        ModelAndView mav = new ModelAndView();


        mav.setViewName("board/getBoardList.html");

        return mav;
    }





}
