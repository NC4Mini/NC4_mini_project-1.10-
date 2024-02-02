package com.nc.project.entity;

import com.nc.project.dto.BoardFileDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "board_file_table")
public class BoardFile extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    public static BoardFile toBoardFileEntity(Board board, String originalFileName, String storedFileName){
        BoardFile boardFile = new BoardFile();
        boardFile.setOriginalFileName(originalFileName);
        boardFile.setStoredFileName(storedFileName);
        boardFile.setBoard(board);
        return boardFile;
    }

    public BoardFileDTO toDTO() {
        return BoardFileDTO.builder()
                .id(this.id)
                .originalFileName(this.originalFileName)
                .storedFileName(this.storedFileName)
                .boardId(this.board.getId())
                .build();
    }
}
