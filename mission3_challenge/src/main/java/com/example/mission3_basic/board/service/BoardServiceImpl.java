package com.example.mission3_basic.board.service;

import com.example.mission3_basic.board.entity.BoardStatus;
import com.example.mission3_basic.board.dto.Board;
import com.example.mission3_basic.board.entity.BoardEntity;
import com.example.mission3_basic.board.exception.InvalidWriterException;
import com.example.mission3_basic.board.exception.NotFoundBoardException;
import com.example.mission3_basic.board.repository.BoardRepository;
import com.example.mission3_basic.user.entity.UserEntity;
import com.example.mission3_basic.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final UserService userService;
    private final BoardRepository boardRepository;

    public BoardServiceImpl(UserService userService, BoardRepository boardRepository) {
        this.userService = userService;
        this.boardRepository = boardRepository;
    }

    @Override
    public Board.ResponseBoard createBoard(Board.RequestBoard requestBoard, UserEntity user) {
        log.info("user {}", user);
        BoardEntity board = new BoardEntity(requestBoard.getTitle(), user);
        BoardEntity savedBoard = this.boardRepository.save(board);
        return Board.ResponseBoard.builder()
                .board(savedBoard)
                .build();

    }

    @Override
    public Board.ResponseBoard updateBoard(Long boardId, Board.RequestBoard requestBoard, UserEntity user) {
        BoardEntity board = getBoardReturnEntity(boardId);
        board.setTitle(requestBoard.getTitle());
        if (!board.getUser().getId().equals(user.getId()))
            throw new InvalidWriterException();

        return Board.ResponseBoard.builder()
                .board(this.boardRepository.save(board))
                .build();
    }

    @Override
    public void deleteBoard(Long boardId, UserEntity user) {
        BoardEntity findBoard = getBoardReturnEntity(boardId);

        if (!user.getId().equals(findBoard.getUser().getId()))
            throw new InvalidWriterException();
        findBoard.delete();
        this.boardRepository.save(findBoard);
    }

    @Override
    public Board.ResponseList getBoard(Long boardId) {
        BoardEntity board = this.boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);
        if (board.getBoardStatus() == BoardStatus.DELETED)
            throw new NotFoundBoardException();

        return Board.ResponseList.builder()
                .board(board)
                .build();
    }

    @Override
    public List<Board.ResponseBoard> getBoards() {
        return this.boardRepository.findAll()
                .stream()
                .filter(board -> board.getBoardStatus() == BoardStatus.CREATED)
                .map(board -> Board.ResponseBoard.builder()
                        .board(board)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public BoardEntity getBoardReturnEntity(Long boardId){
        BoardEntity board = this.boardRepository.findById(boardId)
                .orElseThrow(NotFoundBoardException::new);
        if (board.getBoardStatus() == BoardStatus.DELETED)
            throw new NotFoundBoardException();
        return board;
    }
}
