package com.example.mission2_basic.Board.Service;

import com.example.mission2_basic.Board.Dto.RequestBoard;
import com.example.mission2_basic.Board.Model.Board;
import com.example.mission2_basic.Board.repository.BoardRepository;
import com.example.mission2_basic.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board createBoard(RequestBoard requestBoard){
        Board board = new Board(requestBoard);
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards(){
        return this.boardRepository.findAll();
    }

    public Board getBoardById(Long boardId){
        Board board = this.boardRepository.findById(boardId);
        if (board == null){
            throw new NotFoundException("없는 게시판입니다.");
        }
        return this.boardRepository.findById(boardId);
    }

    public void deleteBoardById(Long boardId){
        this.boardRepository.deleteById(boardId);
    }


    public Board updateBoard(Long boardId, RequestBoard requestBoard) {
        Board findBoard = this.boardRepository.findById(boardId);
        findBoard.update(requestBoard);
        return boardRepository.save(findBoard);
    }

    /**
     * 테스트용 메소드
     */
    public void deleteAllBoards(){
        this.boardRepository.deleteAll();
    }


}
