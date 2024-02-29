package com.hospital.file.service;

import com.hospital.configs.FileProperties;
import com.hospital.file.FileInfoRepository.FileInfoRepository;
import com.hospital.file.entities.FileInfo;
import com.hospital.file.entities.QFileInfo;
import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;

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

    //파일목록 조회
    // mode - ALL : 기본값 - 완료 + 미완료 모두 조회
    //     - DONE : 완료된 파일
    //      - UNDONE : 미완료된 파일
    public List<FileInfo> getList(String gid, String location, String mode){
        QFileInfo fileInfo = QFileInfo.fileInfo; //조건식 비교하기 위해 q형태 객체 가져온다
        //있으면 모드값 없으면 기본값 ALL
        mode = StringUtils.hasText(mode) ? mode : "ALL";

        //여러 조건식 사용 BooleanBuilder 사용
        BooleanBuilder builder = new BooleanBuilder();
        //gid는 필수이므로 무조건 넣어준다
        builder.and(fileInfo.gid.eq(gid));

        //location은 값이 있을때만 조건식을 선별적으로 넣는다
        if(StringUtils.hasText(location)){
            builder.and(fileInfo.location.eq(location));
        }

        //mode값에 따라 완료 , 미완료 2가지 형태 파일


        // UNDONE 일때 false
        if(!mode.equals("ALL")){ //ALL이 아닐때 조건식
            builder.and(fileInfo.done.eq(mode.equals("DONE")));  // DONE 일때 true
        }

        List<FileInfo> items = (List<FileInfo>) repository.findAll(builder, Sort.by(asc("createAt")));
        items.forEach(this::addFileInfo);
        return items;
    }

    //getList 전체파일 조회
    public List<FileInfo> getList(String gid){
        return getList(gid, null, "ALL"); //전체 조회 (완료 + 미완료 파일)

    }

    public List<FileInfo> getList(String gid, String location){
        return getList(gid, location, "ALL"); //location으로 같이 조회했을때 전체 조회
    }

    //완료된 파일만 조회
    public List<FileInfo> getListDone(String gid){
        return getList(gid, null, "DONE"); //완료된 파일만 조회
    }

    public List<FileInfo> getListDone(String gid, String location){
        return getList(gid, location, "DONE"); //완료된 파일만 조회
    }

    //파일 추가 정보 처리
    // - 파일 서버 경로 (filePath)
    // - 파일 URL (fileUrl)

    public void addFileInfo(FileInfo fileInfo){
        long seq = fileInfo.getSeq();
        long dir = seq % 10L;
        String fileName = seq + fileInfo.getExtension();

        //파일경로 , URL
        //서버에 올라가는 실제 파일 경로 filePath
        //브라우저창에서 접근할 수 있는 url를 가공해서 넣음
        String filePath = fileProperties.getPath() + dir + "/" + fileName;
        String fileUrl = request.getContextPath() + fileProperties.getUrl() + dir + "/" + fileName;

        fileInfo.setFilePath(filePath);
        fileInfo.setFileUrl(fileUrl);

        //썸네일 경
    }


}
