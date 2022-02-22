package com.example.mission2_basic.Post.service;

import com.example.mission2_basic.Board.Dto.RequestBoard;
import com.example.mission2_basic.Board.Model.Board;
import com.example.mission2_basic.Board.Service.BoardService;
import com.example.mission2_basic.Board.repository.BoardRepository;
import com.example.mission2_basic.Post.dto.RequestPost;
import com.example.mission2_basic.Post.model.Post;
import com.example.mission2_basic.Post.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostServiceTest {
    private final BoardRepository boardRepository = new BoardRepository();
    private final BoardService boardService = new BoardService(boardRepository);
    private final PostService postService= new PostService(new PostRepository(boardRepository), boardRepository);

    @BeforeEach
    void setUp(){
        boardService.deleteAllBoards();
        postService.deleteAll();
    }
    @Test
    @DisplayName("포스트 생성")
    void createPost(){

        //given
        RequestBoard requestBoard = new RequestBoard("sampleBoard");
        Board savedBoard = boardService.createBoard(requestBoard);
        RequestPost requestPost = new RequestPost("title" ,"writer", "password", "content");


        Post post = this.postService.createPost(savedBoard.getBoardId(), requestPost);
        Assertions.assertThat(post.getId()).isEqualTo(1L);
        Assertions.assertThat(post.getContent()).isEqualTo("content");
        Assertions.assertThat(post.getTitle()).isEqualTo("title");
        Assertions.assertThat(post.getWriter()).isEqualTo("writer");
    }

    @Test
    @DisplayName("여러 개의 포스트들 생성")
    void createPosts(){
        RequestBoard requestBoard = new RequestBoard("sampleBoard");
        Board savedBoard = boardService.createBoard(requestBoard);
        RequestPost requestPost = new RequestPost("title" ,"writer", "password", "content");

        Post post1 = this.postService.createPost(savedBoard.getBoardId(), requestPost);
        Post post2 = this.postService.createPost(savedBoard.getBoardId(), requestPost);


        Board board = this.boardService.getBoardById(1L);
        Assertions.assertThat(board.getPosts().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("포스트 수정")
    void updatePost(){
        RequestBoard requestBoard = new RequestBoard("sampleBoard");
        Board savedBoard = boardService.createBoard(requestBoard);
        RequestPost requestPost = new RequestPost("title" ,"writer", "password", "content");
        RequestPost requestPost1 = new RequestPost("updatedTitle" ,"writer", "password", "content");
        Post post1 = this.postService.createPost(savedBoard.getBoardId(), requestPost);
        Post post2 = this.postService.createPost(savedBoard.getBoardId(), requestPost1);


        RequestPost requestPost2 = new RequestPost(1L,"updatedTitle" ,"writer", "password", "content");
        Post updatedPost = this.postService.update(requestPost2);
        Assertions.assertThat(updatedPost.getTitle()).isEqualTo("updatedTitle");
    }

    @Test
    @DisplayName("포스트 삭제")
    void deletePost(){
        RequestBoard requestBoard = new RequestBoard("sampleBoard");
        Board savedBoard = boardService.createBoard(requestBoard);
        RequestPost requestPost = new RequestPost("title" ,"writer", "password", "content");
        RequestPost requestPost1 = new RequestPost("updatedTitle" ,"writer", "password", "content");
        Post post1 = this.postService.createPost(savedBoard.getBoardId(), requestPost);
        Post post2 = this.postService.createPost(savedBoard.getBoardId(), requestPost1);

        RequestPost post = new RequestPost(1L,"title","writer","password", "content");
        this.postService.deletePost(post);

        Assertions.assertThatThrownBy(() -> this.postService.getPost(1L))
                .hasMessage("요청하신 post가 존재하지 않습니다.");
        Post findPost2 = this.postService.getPost(2L);
        Assertions.assertThat(findPost2).isNotNull();

        Board findBoard = this.boardRepository.findById(savedBoard.getBoardId());
        Assertions.assertThat(findBoard.getPosts().size()).isEqualTo(1);
    }
}