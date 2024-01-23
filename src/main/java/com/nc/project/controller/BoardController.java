package com.nc.project.controller;

import com.nc.project.entity.Board;
import com.nc.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private BoardService boardService;
    //1대 1 문의 페이지 이동
    @GetMapping("/board-list")
    public ModelAndView board(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("board/get_board.html");

        return mav;
    }

    // 1대 1 문의 글쓰기 페이지 이동
    @GetMapping("/getBoardList")
    public ModelAndView getBoardList () {
        ModelAndView mav = new ModelAndView();


        mav.setViewName("board/get_board_list.html");

        return mav;
    }

    @PostMapping("/writedo")
    public String boardWritedo(Board board){
        System.out.println(board.getQuestionTitle());

        boardService.write(board);

        return "";
    }

    @GetMapping("/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        return "boardlist";
    }



}
