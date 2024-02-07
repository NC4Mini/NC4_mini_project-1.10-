package com.nc.project.service;

import com.nc.project.common.FileUtils;
import com.nc.project.dto.BoardDTO;
import com.nc.project.dto.BoardFileDTO;
import com.nc.project.entity.Board;
import com.nc.project.entity.BoardFile;
import com.nc.project.repository.BoardFileRepository;
import com.nc.project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private  final BoardRepository boardRepository;
    private  final BoardFileRepository boardFileRepository;
    private final FileUtils fileUtils;
    public void save(BoardDTO boardDTO) throws IOException {
        //파일 첨부 여부에 따라 로직 분리
        if(ArrayUtils.isEmpty(boardDTO.getBoardFile())){
                System.out.println("첨부파일 없음");
                //첨부 파일 없음.
                Board board = Board.toSaveEntity(boardDTO);
            boardRepository.save(board);
        } else{
            System.out.println("첨부파일 있음");
            //첨부 파일 있음.
            /*
                1.DTO에 담긴 파일을 꺼냄
                2.파일에 이름을 가져옴
                3.서버 저장용 이름으로 수정
                //내사진.jpg =>83943993939_내사진.jpg
                4.저장 경로 설정
                5.해당 경로에 파일 저장
                6.board_table에 해당 데이터 save 처리
                7.board_file_table에 해당 데이터 save 처리
             */

            Board board = Board.toSaveFileEntity(boardDTO);
            Long savedId = boardRepository.save(board).getId();

            MultipartFile[] boardFile = boardDTO.getBoardFile(); //1

            for(MultipartFile multipartFile : boardFile) {
                BoardFileDTO boardFileDTO = fileUtils.parseBoardFileInfo(multipartFile, "board/");

                Board boardEntity = boardRepository.findById(savedId).get();

                BoardFile boardFileEntity = BoardFile.toBoardFileEntity(board, boardFileDTO.getOriginalFileName(), boardFileDTO.getStoredFileName());
                boardFileRepository.save(boardFileEntity);
            }
            //System.out.println("1" + boardDTO.getBoardFile());



        }

    }

    @Transactional
    public List<BoardDTO> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board: boardList){
            boardDTOList.add(BoardDTO.toBoardDTO(board));
        }
        return boardDTOList;
    }


    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if(optionalBoard.isPresent()){
            Board board = optionalBoard.get();
            BoardDTO boardDTO= BoardDTO.toBoardDTO(board);
            return  boardDTO;
        }else{
            return null;
        }
    }

    public void update(BoardDTO boardDTO) {
        Board board = Board.toUpdateEntity(boardDTO);
        boardRepository.save(board);
        //return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<Board> findPaged(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        return boardRepository.findAll(pageRequest);
    }

    public int countTotalPages(int pageSize) {
        long totalItems = boardRepository.count();
        return (int) Math.ceil((double) totalItems / pageSize);
    }


}
