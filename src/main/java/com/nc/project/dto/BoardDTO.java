package com.nc.project.dto;

import com.nc.project.entity.Board;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

//DTO(데이터 전송 객체)
@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
  private Long id;
  private String boardWriter;
  private String boardCategory;
  private String boardTitle;
  private String boardContents;
  private int  boardHits;
  private LocalDateTime boardCreatedTime;
  private LocalDateTime boardUpdatedTime;
  public static  BoardDTO toBoardDTO(Board board){
      BoardDTO boardDTO = new BoardDTO();
      boardDTO.setId(board.getId());
      boardDTO.setBoardWriter(board.getBoardWriter());
      boardDTO.setBoardTitle(board.getBoardTitle());
      boardDTO.setBoardHits(board.getBoardHits());
      boardDTO.setBoardCategory(board.getBoardCategory());
      boardDTO.setBoardContents(board.getBoardContents());
      boardDTO.setBoardCreatedTime(board.getCreatedTime());
      boardDTO.setBoardUpdatedTime(board.getUpdatedTime());
      return boardDTO;
  }
}
