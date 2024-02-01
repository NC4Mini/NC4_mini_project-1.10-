package com.nc.project.common;

import com.nc.project.dto.ItemFileDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class FileUtilsLocal {
    public static ItemFileDTO parseFileInfo(MultipartFile multipartFile, String attachPath) {
        // 리턴할 ItemFileDTO 객체 생성
        ItemFileDTO itemFileDTO = new ItemFileDTO();

        String itemFileOrigin = multipartFile.getOriginalFilename();

        // 같은 파일명으로 파일 업로드 하면 나중에 업로드된 파일로 덮어 써지기 때문에 날짜+랜덤값+파일명으로 파일명 변경
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmsss");
        Date nowDate = new Date();

        String nowDateStr = formater.format(nowDate);

        UUID uuid = UUID.randomUUID();

        // 실제로 db와 서버에 저장될 파일명
        String itemFileName = nowDateStr + "_" + uuid.toString() + "_" + itemFileOrigin;

        // 파일 업로드 될 파일 경로
        String itemFilePath = attachPath;

        // 폴더 존재 여부 확인 및 생성
        File uploadFolder = new File(attachPath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        // 파일 업로드 처리
        File uploadFile = new File(attachPath + itemFileName);

        try {
            multipartFile.transferTo(uploadFile);
        } catch(IOException ie) {
            System.out.println(ie.getMessage());
        }

        // 이미지인지 아닌지 검사
        File checkImage = new File(itemFileOrigin);
        // 파일의 형식 가져오기
        String type = "";

        try {
            type = Files.probeContentType(checkImage.toPath());
        } catch(IOException ie) {
            System.out.println(ie.getMessage());
        }

        if(type != "") {
            if(type.startsWith("image")) {
                itemFileDTO.setItemFileCate("image");
            } else {
                itemFileDTO.setItemFileCate("etc");
            }
        } else {
            itemFileDTO.setItemFileCate("etc");
        }

        // 리턴될 DTO에 값들 세팅
        itemFileDTO.setItemFileName(itemFileName);
        itemFileDTO.setItemFilePath(itemFilePath);
        itemFileDTO.setItemFileOrigin(itemFileOrigin);

        return itemFileDTO;
    }
}

