package com.nc.project.dto;

import com.nc.project.entity.Board;
import com.nc.project.entity.BoardFile;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

  private MultipartFile[] boardFile; //save.html => Controller 파일 담는 용도
  private String originalFileName;  //원본 파일 이름
  private String storedFileName; // 서버 저장용 파일이름
  private int fileAttached; //파일 첨부 여부(첨부1, 미첨부0)
  private List<BoardFileDTO> boardFileList;

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
      boardDTO.setBoardFileList(board.getBoardFileList().stream().map(boardFlie1 -> boardFlie1.toDTO()
      ).toList());
      if(board.getFileAttached() == 0){
          boardDTO.setFileAttached(board.getFileAttached()); //0
      }else{
          boardDTO.setFileAttached(board.getFileAttached()); //1
          //파일 이름을 가져가야 함.
          boardDTO.setOriginalFileName(board.getBoardFileList().get(0).getOriginalFileName());
          boardDTO.setStoredFileName(board.getBoardFileList().get(0).getStoredFileName());
      }
      return boardDTO;
  }
}

