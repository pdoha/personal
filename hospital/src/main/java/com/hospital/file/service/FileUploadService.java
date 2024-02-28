package com.hospital.file.service;

import com.hospital.commons.Utils;
import com.hospital.configs.FileProperties;
import com.hospital.file.FileInfoRepository.FileInfoRepository;
import com.hospital.file.entities.FileInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileUploadService {

    private final FileProperties fileProperties;
    private final FileInfoRepository repository;
    private final FileInfoService infoService; //추가정보
    private final Utils utils;
    public List<FileInfo> upload(MultipartFile[] files, String gid, String location){


        //gid 없으면 기본값
        gid = StringUtils.hasText(gid) ? gid : UUID.randomUUID().toString();
        //기본 경로 만들기 ( uploadPath )
        String uploadPath = fileProperties.getPath(); //(파일 업로드 기본 경로)

        //업로드 성공 파일 정보를 목록에 담아준다
        List<FileInfo> uploadedFiles = new ArrayList<>();


        // 1. 파일 정보 저장
        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename(); //업로드시 원본 파일명
            String extension = fileName.substring(fileName.lastIndexOf(".")); //확장자 추출
            String fileType = file.getContentType(); //파일 종류 ( 이미지 파일 같은 ..)

            FileInfo fileInfo = FileInfo.builder()
                    .gid(gid)
                    .location(location)
                    .fileName(fileName)
                    .extension(extension)
                    .fileType(fileType)
                    .build();

            //DB 저장
            repository.saveAndFlush(fileInfo);

            // 2. 서버쪽에 파일 업로드 처리 ( 디렉토리가 없으면 오류 발생하니까)
            long seq = fileInfo.getSeq();
            File dir = new File(uploadPath + (seq % 10));
            //디렉토리가 없으면 -> 생성  mkdir
            if(!dir.exists()){
                dir.mkdir();

            }

            //실제 파일 경로 ( uploadFile) 만들기
            File uploadFile = new File(dir, seq  + extension); // 파일명 = 증감번호 확장자
            try {
                //업로드 성공시
                file.transferTo(uploadFile);

                infoService.addFileInfo(fileInfo); //파일 추가 정보 처리

                uploadedFiles.add(fileInfo);  //업로드 성공시 파일정보를 넣는다
            } catch (IOException e) {
                //업로드 실패시
                e.printStackTrace(); //예외 정보만 확인
                //실패했을때 파일 정보를 ( 디비 기록을 )지운다
                repository.delete(fileInfo); //업로드 실패시
                repository.flush();
            }


        }


        //반환값은 업로드된 파일
        return uploadedFiles;
    }

    //업로드 완료 처리 done
    public void processDone(String gid) {
        List<FileInfo> files = repository.findByGid(gid);
        //gid가 널이면, 처리 x
        if(files == null){
            return;
        }
        //파일 정보가 넘어왔을때 done = true 로 바꿔준다
        files.forEach(file -> file.setDone(true));
        repository.flush();
    }
}
