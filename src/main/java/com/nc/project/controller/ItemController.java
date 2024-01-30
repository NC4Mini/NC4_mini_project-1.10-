package com.nc.project.controller;

import com.nc.project.common.FileUtilsLocal;
import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.ItemFileDTO;
import com.nc.project.dto.ResponseDTO;
import com.nc.project.entity.Item;
import com.nc.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // 상품 등록 페이지 이동
    @GetMapping("/add-item")
    public ModelAndView addItemView() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("item/add_item.html");

        return mav;
    }

    @PostMapping("/add-item")
    public ResponseEntity<?> addItem(ItemDTO itemDTO,
                                     @RequestParam("uploadFiles") MultipartFile[] uploadFiles) {
        ResponseDTO<Map<String, String>> response = new ResponseDTO<>();

        try {
            List<ItemFileDTO> itemFileDTOList = new ArrayList<>();

            for (MultipartFile file : uploadFiles) {
                if (file.getOriginalFilename() != null &&
                        !file.getOriginalFilename().equals("")) {
                    // item이라는 디렉토리에 저장 (클라우드 버켓에서 자동으로 인식해줌)
                    ItemFileDTO itemFileDTO = FileUtilsLocal.parseFileInfo(file, "item/");
                    itemFileDTOList.add(itemFileDTO);
                }
            }
            itemDTO.setItemFileDTOList(itemFileDTOList);
            itemService.insertItem(itemDTO);
            Map<String, String> returnMap = new HashMap<>();

            returnMap.put("msg", "정상적으로 입력되었습니다.");

            response.setItem(returnMap);
            response.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            if (itemDTO.getItemName().equals("")) {
                response.setErrorCode(602);
                response.setErrorMessage("게시글 제목을 입력하세요.");
            } else if (itemDTO.getItemDescription().equals("")) {
                response.setErrorCode(603);
                response.setErrorMessage("설명을 입력해주세요.");
            } else if (itemDTO.getItemPrice() == 0) {
                response.setErrorCode(604);
                response.setErrorMessage("가격을 입력해주세요.");
            } else {
                response.setErrorCode(605);
                response.setErrorMessage(e.getMessage());
            }
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 메인에서 상품 상세페이지 이동
    @GetMapping("/item-detail")
    public ModelAndView getItemDetail (@RequestParam("itemId") long itemId) {
        Item item = itemService.getItem(itemId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("item", item);
        mav.setViewName("item/item_detail_test.html");

        return mav;
    }
}
