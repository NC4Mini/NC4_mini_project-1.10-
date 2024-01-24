package com.nc.project.service;

import com.nc.project.dto.BoardDTO;
import com.nc.project.entity.Board;
import com.nc.project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private  final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        Board board = Board.toSaveEntity(boardDTO);
        boardRepository.save(board);
    }

    public List<BoardDTO> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board: boardList){
            boardDTOList.add(BoardDTO.toBoardDTO(board));
        }
        return boardDTOList;
    }


}
