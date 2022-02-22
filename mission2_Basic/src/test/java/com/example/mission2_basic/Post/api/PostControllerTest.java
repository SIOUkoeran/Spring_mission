package com.example.mission2_basic.Post.api;

import com.example.mission2_basic.Board.Dto.RequestBoard;
import com.example.mission2_basic.Board.Service.BoardService;
import com.example.mission2_basic.Post.dto.RequestPost;
import com.example.mission2_basic.Post.model.Post;
import com.example.mission2_basic.Post.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostService postService;

    @Autowired
    BoardService boardService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        postService.deleteAll();
        boardService.deleteAllBoards();
    }
    @Test
    @DisplayName("포스트 생성")
    void createPost() throws Exception{
        createBoard();

        RequestPost requestPost = new RequestPost("postTitle", "writer", "password1", "content");
        mockMvc.perform(post("/api/board/1/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestPost)))
                .andExpect(jsonPath("data.title").value("postTitle"))
                .andExpect(jsonPath("data.writer").value("writer"))
                .andExpect(jsonPath("data.content").value("content"))
                .andExpect(jsonPath("data.postId").value(1))
                .andExpect(jsonPath("data.boardId").value(1))
                .andExpect(jsonPath("code").value("2010"))
                .andExpect(jsonPath("message").value("CREATE_POST"))
                .andDo(print());
    }

    @Test
    @DisplayName("포스트 수정")
    void updatePost() throws Exception{
        createBoard();
        RequestPost requestPost = new RequestPost("postTitle", "writer", "password1", "content");
        RequestPost updateRequestPost = new RequestPost(1L, 1L,"updatePostTitle", "writer", "password1", "content");
        this.postService.createPost(1L ,requestPost);

        mockMvc.perform(put("/api/post/update")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequestPost)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.id").value(1L))
                .andExpect(jsonPath("data.title").value("updatePostTitle"))
                .andExpect(jsonPath("data.writer").value("writer"))
                .andExpect(jsonPath("data.password").value("password1"))
                .andExpect(jsonPath("data.boardId").value(1L))
                .andDo(print());

    }
    @Test
    @DisplayName("포스트 삭제")
    void deletePost() throws Exception{
        createBoard();
        RequestPost requestPost = new RequestPost("postTitle", "writer", "password1", "content");
        this.postService.createPost(1L, requestPost);

        RequestPost inputPost = new RequestPost(1L,"postTitle", "writer", "password1", "content");

        mockMvc.perform(delete("/api/post/delete")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputPost)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("같은 게시판 아이디의 포스트들 받아오기")
    void getPost() throws Exception{
        createBoard();
        RequestPost requestPost;
        for (int i = 1; i < 11; i++) {
            requestPost = new RequestPost("postTitle " + i, "writer", "password1", "content");
            this.postService.createPost(1L, requestPost);
        }
        mockMvc.perform(get("/api/board/1/post")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("data[0].title").value("postTitle 1"))
                .andExpect(jsonPath("data[5].title").value("postTitle 6"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    private void createBoard(){

        RequestBoard requestBoard = new RequestBoard("requestTitle");
        this.boardService.createBoard(requestBoard);
    }
}