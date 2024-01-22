package com.nc.project.controller;

import com.nc.project.dto.BoardDTO;
import com.nc.project.entity.Board;
import com.nc.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")

public class BoardController {
    private final BoardService boardService;

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

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "board/getBoard";
    }



}
