package com.nc.project.entity;
import com.nc.project.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="board")
public class Board extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String boardWriter;

    @Column // 크기 255, null 가능
    private String boardCategory;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;
//
    @Column
    private int boardHits;

    @Column
    private  int fileAttached; //1 or 0

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFile> boardFileList = new ArrayList<>();
    //파일이 없을때 호출
    public static Board toSaveEntity(BoardDTO boardDTO){
        Board board = new Board();
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardCategory(boardDTO.getBoardCategory());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardHits(0);
        board.setFileAttached(0); //파일 없음.
        return board;
    }


    public static Board toUpdateEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setBoardHits(boardDTO.getBoardHits());
        board.setId(boardDTO.getId());
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardCategory(boardDTO.getBoardCategory());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setFileAttached(boardDTO.getFileAttached());
        return board;
    }

    public static Board toSaveFileEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardCategory(boardDTO.getBoardCategory());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardHits(0);
        board.setFileAttached(1); //파일 있음.
        return board;
    }
}
