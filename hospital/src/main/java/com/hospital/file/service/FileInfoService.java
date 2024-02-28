package com.hospital.file.service;

import com.hospital.configs.FileProperties;
import com.hospital.file.FileInfoRepository.FileInfoRepository;
import com.hospital.file.entities.FileInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileInfoService {
    private final FileProperties fileProperties;
    private final FileInfoRepository repository;
    private final HttpServletRequest request;


    //단일 조회 - 파일번호 seq
    public FileInfo get(Long seq){
        FileInfo fileInfo = repository.findById(seq)
                .orElseThrow(FileNotFoundException::new);

        //파일 추가 정보
        addFileInfo(fileInfo);
        return fileInfo;
    }

    //파일 추가 정보 처리
    // - 파일 서버 경로 (filePath)
    // - 파일 URL (fileUrl)

    public void addFileInfo(FileInfo fileInfo){
        long seq = fileInfo.getSeq();
        long dir = seq % 10L;
        String fileName = seq + fileInfo.getExtension();

        //서버에 올라가는 실제 파일 경로 filePath
        //브라우저창에서 접근할 수 있는 url를 가공해서 넣음
        String filePath = fileProperties.getPath() + dir + "/" + fileName;
        String fileUrl = request.getContextPath() + fileProperties.getUrl() + dir + "/" + fileName;

        fileInfo.setFilePath(filePath);
        fileInfo.setFileUrl(fileUrl);
    }
}
