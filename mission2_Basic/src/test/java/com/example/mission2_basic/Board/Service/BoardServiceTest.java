package com.example.mission2_basic.Board.Service;

import com.example.mission2_basic.Board.Dto.RequestBoard;
import com.example.mission2_basic.Board.Model.Board;
import com.example.mission2_basic.Board.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class BoardServiceTest {

    private final BoardService boardService = new BoardService(new BoardRepository());

    @BeforeEach
    void setUp(){
        this.boardService.deleteAllBoards();
        System.out.println("delete all");
    }

    @Test
    @DisplayName("게시판 생성")
    void createBoard(){
        RequestBoard requestBoard = new RequestBoard("sampleBoard");
        Board createdBoard = this.boardService.createBoard(requestBoard);
        assertThat(requestBoard.getTitle()).isEqualTo(createdBoard.getTitle());
        assertThat(1L).isEqualTo(createdBoard.getBoardId());
    }

    @Test
    @DisplayName("게시판 여러개 생성")
    void createBoards(){
        RequestBoard requestBoard;

        for (int i = 1; i < 10; i++) {
            requestBoard = new RequestBoard("sampleBoard" + i);
            Board createdBoard = this.boardService.createBoard(requestBoard);
            assertThat(createdBoard.getBoardId()).isEqualTo(Long.valueOf(i));
            assertThat(createdBoard.getTitle()).isEqualTo("sampleBoard" + i);
        }
    }

    @Test
    @DisplayName("게시판들 찾기")
    void getBoard(){
        createBoards10();
        List<Board> boardList = this.boardService.getAllBoards();
        Assertions.assertThat(boardList.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("게시판 찾기")
    void getAllBoards(){
        createBoards10();
        Board findBoard = this.boardService.getBoardById(3L);
        Assertions.assertThat(findBoard.getBoardId()).isEqualTo(3L);
    }

    @Test
    @DisplayName("게시판 수정")
    void updateBoard(){
        createBoards10();
        Board findBoard = this.boardService.getBoardById(3L);
        RequestBoard requestBoard = new RequestBoard("test");
        this.boardService.updateBoard(findBoard.getBoardId(), requestBoard);
        Board updatedBoard = this.boardService.getBoardById(3L);
        Assertions.assertThat(updatedBoard.getTitle()).isEqualTo("test");
    }

    @Test
    @DisplayName("게시판 삭제")
    void deleteBoard(){
        createBoards10();
        this.boardService.deleteBoardById(3L);
        Assertions.assertThatThrownBy(() -> this.boardService.getBoardById(3L))
                .hasMessage("없는 게시판입니다.");
    }

    void createBoards10(){
        RequestBoard requestBoard;

        for (int i = 1; i <= 10; i++) {
            requestBoard = new RequestBoard("sampleBoard" + i);
            Board createdBoard = this.boardService.createBoard(requestBoard);
        }
    }
}