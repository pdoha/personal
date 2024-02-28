package com.hospital.file.service;

import com.hospital.configs.FileProperties;
import com.hospital.file.entities.FileInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileUploadService {

    private final FileProperties fileProperties;
    private final FileProperties repository;
    public List<FileInfo> upload(MultipartFile[] files, String gid, String location){


        //gid 없으면 기본값
        gid = StringUtils.hasText(gid) ? gid : UUID.randomUUID().toString();

        // 1. 파일 정보 저장
        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename(); //업로드시 원파일명
            String extension = fileName.substring(fileName.lastIndexOf(".")); //확장자
            String fileType = file.getContentType(); //파일 종류 ( 이미지 파일 같은 ..)

            FileInfo fileInfo = FileInfo.builder()
                    .gid(gid)
                    .location(location)
                    .fileName(fileName)
                    .extension(extension)
                    .fileType(fileType)
                    .build();

            repository.saveAndFlush(fileInfo);

        }
        // 2. 서버쪽에 파일 업로드 처리

        return null;
    }
}
