package com.example.mission3_basic.board.repository;

import com.example.mission3_basic.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Optional<BoardEntity> findById(Long boardId);
}
