package com.example.mission3_basic.board.service;

import com.example.mission3_basic.board.dto.Board;
import com.example.mission3_basic.board.entity.BoardEntity;
import com.example.mission3_basic.user.entity.UserEntity;

import java.util.List;

public interface BoardService {
    Board.ResponseBoard createBoard(Board.RequestBoard requestBoard, UserEntity user);
    Board.ResponseBoard updateBoard(Long boardId, Board.RequestBoard requestBoard, UserEntity user);
    void deleteBoard(Long boardId, UserEntity user);
    Board.ResponseList getBoard(Long boardId);
    List<Board.ResponseBoard> getBoards();
    BoardEntity getBoardReturnEntity(Long boardId);
}
