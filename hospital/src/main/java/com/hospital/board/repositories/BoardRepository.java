package com.hospital.board.repositories;

import com.hospital.board.entities.BoardData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardData, Long> {
}
