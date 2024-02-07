package com.nc.project.controller;

import com.nc.project.common.FileUtils;
import com.nc.project.common.FileUtilsLocal;
import com.nc.project.dto.ItemDTO;
import com.nc.project.dto.ItemFileDTO;
import com.nc.project.dto.ResponseDTO;
import com.nc.project.entity.Item;
import com.nc.project.entity.ItemFile;
import com.nc.project.service.ItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    private final FileUtils fileUtils;


    // 상품 등록 페이지 이동
    @GetMapping("/item-add")
    public ModelAndView itemAddView() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("item/item_add.html");

        return mav;
    }

    @PostMapping("/item-add")
    public ResponseEntity<?> itemAdd(ItemDTO itemDTO,
                                     @RequestParam(value = "item_main_image") MultipartFile itemMainImage,
                                     @RequestParam(value = "item_detail_image") MultipartFile itemDetailImage,
                                     @RequestParam(value = "item_thumbnail_image") MultipartFile itemThumbnailImage) {

        ResponseDTO<Map<String, String>> response = new ResponseDTO<>();

        try {
            List<ItemFileDTO> itemFileDTOList = new ArrayList<>();

            if (itemMainImage.getOriginalFilename() != null &&
                    !itemMainImage.getOriginalFilename().isEmpty()) {

                ItemFileDTO itemFileDTO = fileUtils.parseFileInfo(itemMainImage, "item/");
                itemFileDTO.setItemType("Main");
                itemFileDTOList.add(itemFileDTO);
            }
            if (itemDetailImage.getOriginalFilename() != null &&
                    !itemDetailImage.getOriginalFilename().isEmpty()) {
                // item이라는 디렉토리에 저장 (클라우드 버켓에서 자동으로 인식해줌)
                ItemFileDTO itemFileDTO = fileUtils.parseFileInfo(itemDetailImage, "item/");
                itemFileDTO.setItemType("Detail");
                itemFileDTOList.add(itemFileDTO);
            }
            if (itemThumbnailImage.getOriginalFilename() != null &&
                    !itemThumbnailImage.getOriginalFilename().isEmpty()) {

                ItemFileDTO itemFileDTO = fileUtils.parseFileInfo(itemThumbnailImage, "item/");
                itemFileDTO.setItemType("Thumbnail");
                itemFileDTOList.add(itemFileDTO);
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
                response.setErrorMessage("상품 이름을 입력하세요.");
            } else if (itemDTO.getItemDescription().equals("")) {
                response.setErrorCode(603);
                response.setErrorMessage("상품 설명을 입력해주세요.");
            } else if (itemDTO.getItemPrice() == 0) {
                response.setErrorCode(604);
                response.setErrorMessage("상품 가격을 입력해주세요.");
            } else {
                response.setErrorCode(605);
                response.setErrorMessage(e.getMessage());
            }
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Transactional
    @PutMapping("/item-modify")
    public ResponseEntity<?> itemModify(ItemDTO itemDTO,
                                        @RequestParam(value = "item_main_image", required = false) MultipartFile itemMainImage,
                                        @RequestParam(value = "item_detail_image", required = false) MultipartFile itemDetailImage,
                                        @RequestParam(value = "item_thumbnail_image", required = false) MultipartFile itemThumbnailImage) {

        ResponseDTO<Map<String, String>> response = new ResponseDTO<>();

            try {
                ItemDTO originItemDTO = itemService.getItem(itemDTO.getItemId()).toDTO();

                List<ItemFileDTO> itemFileDTOList = new ArrayList<>();

                if (itemMainImage.getOriginalFilename() != null &&
                        !itemMainImage.getOriginalFilename().isEmpty()) {

                    ItemFileDTO itemFileDTO = fileUtils.parseFileInfo(itemMainImage, "item/");
                    itemFileDTO.setItemType("Main");
                    itemFileDTOList.add(itemFileDTO);
                } else {
                    itemFileDTOList.addAll(
                            originItemDTO.getItemFileDTOList()
                                    .stream()
                                    .filter(itemFileDTO -> itemFileDTO.getItemType().equalsIgnoreCase("main"))
                                    .toList()
                    );
                }

                if (itemDetailImage.getOriginalFilename() != null &&
                        !itemDetailImage.getOriginalFilename().isEmpty()) {
                    ItemFileDTO itemFileDTO = fileUtils.parseFileInfo(itemDetailImage, "item/");
                    itemFileDTO.setItemType("Detail");
                    itemFileDTOList.add(itemFileDTO);
                } else {
                    itemFileDTOList.addAll(
                            originItemDTO.getItemFileDTOList()
                                    .stream()
                                    .filter(itemFileDTO -> itemFileDTO.getItemType().equalsIgnoreCase("detail"))
                                    .toList());
                }

                if (itemThumbnailImage.getOriginalFilename() != null &&
                        !itemThumbnailImage.getOriginalFilename().isEmpty()) {

                    ItemFileDTO itemFileDTO = fileUtils.parseFileInfo(itemThumbnailImage, "item/");
                    itemFileDTO.setItemType("Thumbnail");
                    itemFileDTOList.add(itemFileDTO);
                } else {
                    itemFileDTOList.addAll(
                            originItemDTO.getItemFileDTOList()
                                    .stream()
                                    .filter(itemFileDTO -> itemFileDTO.getItemType().equalsIgnoreCase("thumbnail"))
                                    .toList());
                }

                itemDTO.setItemFileDTOList(itemFileDTOList);


                itemService.modifyItem(itemDTO);
                Map<String, String> returnMap = new HashMap<>();

                returnMap.put("msg", "정상적으로 수정되었습니다.");

                response.setItem(returnMap);
                response.setStatusCode(HttpStatus.OK.value());

                return ResponseEntity.ok(response);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                if (itemDTO.getItemName().equals("")) {
                    response.setErrorCode(602);
                    response.setErrorMessage("상품 이름을 입력하세요.");
                } else if (itemDTO.getItemDescription().equals("")) {
                    response.setErrorCode(603);
                    response.setErrorMessage("상품 설명을 입력해주세요.");
                } else if (itemDTO.getItemPrice() == 0) {
                    response.setErrorCode(604);
                    response.setErrorMessage("상품 가격을 입력해주세요.");
                } else {
                    response.setErrorCode(605);
                    response.setErrorMessage(e.getMessage());
                }
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.badRequest().body(response);
            }
        }

        // 상품 관리창으로 이동
        @GetMapping("/item-manage")
        public ModelAndView itemManageView (@PageableDefault(page = 0, size = 12) Pageable pageable,
                ItemDTO itemDTO){
            ModelAndView mav = new ModelAndView();

            // 페이지리스트에 아이템리스트 모든 요소 추가
            Page<Item> pageList = itemService.ItemList(pageable);
            mav.addObject("itemList", pageList);

            // 리스트에 속한 총 엘리먼트 갯수 호출
            long totalItemCount = pageList.getTotalElements();
            mav.addObject("totalItemCount", totalItemCount);

            mav.setViewName("item/item_manage.html");

            return mav;
        }

        @GetMapping("/item-modify")
        public ModelAndView itemModifyView ( @RequestParam("itemId") long itemId){

            ModelAndView mav = new ModelAndView();

            Item item = itemService.getItem(itemId);

            List<ItemFile> itemFileList = item.getItemFileList();

            ItemFile mainFile = null;
            ItemFile detailFile = null;
            ItemFile thumbnailFile = null;

            // for-each를 통한 리스트 순회로 코드 줄이기
            for (ItemFile itemFile : itemFileList) {
                if (itemFile.getItemType().equalsIgnoreCase("main")) {
                    mainFile = itemFile;
                } else if (itemFile.getItemType().equalsIgnoreCase("detail")) {
                    detailFile = itemFile;
                } else {
                    thumbnailFile = itemFile;
                }
            }

            mav.addObject("item", item);
            mav.addObject("mainFile", mainFile);
            mav.addObject("detailFile", detailFile);
            mav.addObject("thumbnailFile", thumbnailFile);

            mav.setViewName("item/item_modify.html");

            return mav;
        }


         // 메인에서 상품 상세페이지 이동
    @GetMapping("/item-detail")
    public ModelAndView getItemDetail(@RequestParam("itemId") long itemId) {
        ModelAndView mav = new ModelAndView();

        Item item = itemService.getItem(itemId);

        List<ItemFile> itemFileList = item.getItemFileList();

        ItemFile mainFile = null;
        ItemFile detailFile = null;
        ItemFile thumbnailFile = null;
        String defaultImgPath = "default_Img.png";
        
        // for-each를 통한 리스트 순회로 코드 줄이기
        for(ItemFile itemFile : itemFileList) {
            if(itemFile.getItemType().equalsIgnoreCase("main")) {
                mainFile = itemFile;
            } else if(itemFile.getItemType().equalsIgnoreCase("detail")) {
                detailFile = itemFile;
            } else {
                thumbnailFile = itemFile;
            }
        }
        
        mav.addObject("item", item);
        mav.addObject("mainFile", mainFile);
        mav.addObject("detailFile", detailFile);
        mav.addObject("thumbnailFile", thumbnailFile);
        mav.addObject("defaultImgPath", defaultImgPath);


        mav.setViewName("item/item_detail.html");

        return mav;
    }

        @PostMapping("/item-delete")
        public ResponseEntity<?> itemDelete ( @RequestParam("itemId") long itemId){
            ResponseDTO<Map<String, String>> response = new ResponseDTO<>();
            System.out.println(itemId);
            try {
                itemService.deleteItem(itemId);

                Map<String, String> returnMap = new HashMap<>();

                returnMap.put("msg", "정상적으로 삭제되었습니다.");

                response.setItem(returnMap);
                response.setStatusCode(HttpStatus.OK.value());

                return ResponseEntity.ok(response);
            } catch (Exception e) {
                System.out.println(e.getMessage());

                response.setErrorCode(801);
                response.setErrorMessage(e.getMessage());
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());

                return ResponseEntity.badRequest().body(response);
            }
        }
    }

