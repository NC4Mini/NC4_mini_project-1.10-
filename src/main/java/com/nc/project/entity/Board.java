package com.nc.project.entity;
import com.nc.project.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
//    @Column
//    private int boardHits;
    public static Board toSaveEntity(BoardDTO boardDTO){
        Board board = new Board();
//        board.setBoardHits(boardDTO.getBoardHits());
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardCategory(boardDTO.getBoardCategory());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents(boardDTO.getBoardContents());
        return board;
    }



}
