package com.nc.project.controller;

import com.nc.project.dto.BoardDTO;
import com.nc.project.entity.Board;
import com.nc.project.service.BoardService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
//    @GetMapping("/board-list")
//    public ModelAndView board(){
//        ModelAndView mav = new ModelAndView();
//
//        mav.setViewName("board/getBoardList.html");
//
//        return mav;
//    }

    // 1대 1 문의 글쓰기 페이지 이동
    @GetMapping("/insert-board")
    public ModelAndView getBoardList () {
        ModelAndView mav = new ModelAndView();


        mav.setViewName("board/insertBoard.html");

        return mav;
    }

    @PostMapping("/save")
    public void save(@ModelAttribute BoardDTO boardDTO, HttpServletResponse response) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        response.sendRedirect("/board/board-list");
    }

    //데이터 가져올때 모델객체
    @GetMapping("/board-list")
    public String findAll(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "5") int pageSize,
                          Model model) {
        Page<Board> boardPage = boardService.findPaged(page, pageSize);
        List<Board> boardList = boardPage.getContent();

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", boardPage.getNumber() + 1);
        model.addAttribute("totalPages", boardPage.getTotalPages());

        return "board/getBoardList";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
//   해당 게시글 조회수를 하나 올리고 게시글 데이터를 가져와서 detail.html에 출력
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        System.out.println("===================================");
        System.out.println(boardDTO);
        model.addAttribute("board", boardDTO);
        return "board/getBoardDetail";

    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "board/updateBoard";
    }

    @Transactional
    @PostMapping("/update")
    public void update(@ModelAttribute BoardDTO boardDTO, HttpServletResponse response) throws IOException {
         boardService.update(boardDTO);
        response.sendRedirect("/board/" + boardDTO.getId());
//        return "redirect:/board/" + boardDTO.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/board-list";
    }

}
//getBoardList.html
