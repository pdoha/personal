package com.hospital.board.controllers;

import com.hospital.board.entities.BoardData;
import com.hospital.board.repositories.BoardDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardDataRepository boardDataRepository;

    @ResponseBody //void로 넣어서 응답내용 없어도됨
    @GetMapping("/test")
    public void test(){
        //기본데이터 가져와서
        BoardData data = boardDataRepository.findById(1L).orElse(null);
        //수정
        data.setSubject("(수정)제목");
        boardDataRepository.flush();
     /*   BoardData data = new BoardData();
        //회원정보는 로그인한 회원정보로 알아서 들어감
        data.setSubject("제목");
        data.setContent("내용");
        boardDataRepository.saveAndFlush(data);*/

    }
}
