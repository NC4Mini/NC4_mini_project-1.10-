package com.nc.project.controller;

import com.nc.project.dto.BoardDTO;
import com.nc.project.service.BoardService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

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
    public void save(@ModelAttribute BoardDTO boardDTO, HttpServletResponse response) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        response.sendRedirect("/board/getBoard");
    }

    //데이터 가져올때 모델객체
    @GetMapping("/getBoard")
    public String findAll(Model model){
        //DB에서 전체 게시글 데이터를 가져와서 getBoard.html에 보여준다
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "board/getBoard";
    }


}
