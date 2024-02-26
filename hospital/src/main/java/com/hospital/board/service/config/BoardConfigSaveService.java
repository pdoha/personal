package com.hospital.board.service.config;

import com.hospital.admin.board.controllers.RequestBoardConfig;
import com.hospital.board.entities.Board;
import com.hospital.board.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardConfigSaveService {

    private final BoardRepository boardRepository;

    public void save(RequestBoardConfig form){

        //bid 기본키 불러와서
        String bid = form.getBid();

        //있으면 값넣어주고, 없으면 새로 추가
        Board board = boardRepository.findById(bid).orElseGet(Board::new);

    }
}
