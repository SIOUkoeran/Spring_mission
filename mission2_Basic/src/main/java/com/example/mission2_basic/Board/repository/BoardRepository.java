package com.example.mission2_basic.Board.repository;

import com.example.mission2_basic.Board.Model.Board;
import com.example.mission2_basic.Post.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Slf4j
public class BoardRepository {
    private static final HashMap<Long, Board> boardStored = new HashMap<>();
    private Long boardId = 0L;

    public Board save(Board createdBoard) {
        if (createdBoard.getBoardId() == null){
            Board tempBoard = new Board(++boardId, createdBoard);
            boardStored.put(boardId, tempBoard);
            return boardStored.get(tempBoard.getBoardId());
        }else{
            boardStored.replace(createdBoard.getBoardId(),new Board(createdBoard.getBoardId(), createdBoard));

            return boardStored.get(createdBoard.getBoardId());
        }
    }

    public List<Board> findAll(){
        return new ArrayList<>(boardStored.values());
    }

    public Board findById(Long boardId){
        return boardStored.get(boardId);
    }

    public void deleteAll(){
        boardStored.clear();
        boardId = 0L;
    }


    public void deleteById(Long boardId) {
        Board board = boardStored.get(boardId);
        boardStored.remove(boardId);
    }
}
