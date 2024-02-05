package com.nc.project.common;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nc.project.configuration.NaverConfiguration;

import com.nc.project.dto.BoardFileDTO;
import com.nc.project.dto.ItemFileDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class FileUtils {
    private final AmazonS3 s3;

    public FileUtils(NaverConfiguration naverConfiguration) {
        s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        naverConfiguration.getEndPoint(), naverConfiguration.getRegionName()
                ))
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(
                                naverConfiguration.getAccessKey(), naverConfiguration.getSecretKey()
                        )
                ))
                .build();
    }

    public ItemFileDTO parseFileInfo(MultipartFile multipartFile, String directory) {
        //버킷 이름
        String bucketName = "bit-nc4th-miniproject";

        // 리턴할 BoardFileDTO 객체 생성
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
        String itemFilePath = directory;

        // 오브젝트 스토리지에 파일 업로드
        try(InputStream fileIputStream = multipartFile.getInputStream()) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    directory + itemFileName,
                    fileIputStream,
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            s3.putObject(putObjectRequest);
        } catch(Exception e) {
            System.out.println(e.getMessage());
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


    public BoardFileDTO parseBoardFileInfo(MultipartFile multipartFile, String directory) {
        //버킷 이름
        String bucketName = "bit-nc4th-miniproject";
        BoardFileDTO boardFileDTO = new BoardFileDTO();
        boardFileDTO.setOriginalFileName(multipartFile.getOriginalFilename());
        boardFileDTO.setStoredFileName(System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename());


        // 오브젝트 스토리지에 파일 업로드
        try(InputStream fileIputStream = multipartFile.getInputStream()) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    directory + boardFileDTO.getStoredFileName(),
                    fileIputStream,
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            s3.putObject(putObjectRequest);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }


        
        return boardFileDTO;
    }

}
