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
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
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
                .andDo(print())
                .andDo(document("post-create",
                        requestHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("request http content header")
                        ),
                        relaxedRequestFields(
                                fieldWithPath("title").description("post title"),
                                fieldWithPath("writer").description("post writer"),
                                fieldWithPath("password").description("post password"),
                                fieldWithPath("boardId").description("post board Id"),
                                fieldWithPath("content").description("post content")
                        ),
                        responseFields(
                                fieldWithPath("data").description("response data"),
                                fieldWithPath("message").description("response message"),
                                fieldWithPath("code").description("response custom code (HttpStatus is always 200. so, code is real status code)"),
                                fieldWithPath("data.postId").description("created post id"),
                                fieldWithPath("data.writer").description("created post writer"),
                                fieldWithPath("data.title").description("created post title"),
                                fieldWithPath("data.content").description("created post content"),
                                fieldWithPath("data.boardId").description("created post board id")
                        )
                ));
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
                .andDo(print())
                .andDo(document("post-update",
                        requestHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("request http content header")
                        ),
                        requestFields(
                                fieldWithPath("id").description("post id"),
                                fieldWithPath("title").description("post title"),
                                fieldWithPath("writer").description("post writer"),
                                fieldWithPath("password").description("post password"),
                                fieldWithPath("boardId").description("post board Id"),
                                fieldWithPath("content").description("post content")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("data").description("response data"),
                                fieldWithPath("message").description("response message"),
                                fieldWithPath("code").description("response custom code (HttpStatus is always 200. so, code is real status code)"),
                                fieldWithPath("data.id").description("revised post id"),
                                fieldWithPath("data.writer").description("revised post writer"),
                                fieldWithPath("data.title").description("revised post title"),
                                fieldWithPath("data.content").description("revised post content"),
                                fieldWithPath("data.boardId").description("revised post board id")
                        )
                ))
        ;

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
                .andDo(print())
                .andDo(document("post-delete",
                        requestHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("request http content header")
                        ),
                        requestFields(
                                fieldWithPath("id").description("post id"),
                                fieldWithPath("title").description("post title"),
                                fieldWithPath("writer").description("post writer"),
                                fieldWithPath("password").description("post password"),
                                fieldWithPath("boardId").description("post board Id"),
                                fieldWithPath("content").description("post content")
                        ),
                        responseFields(
                                fieldWithPath("data").description("response data"),
                                fieldWithPath("message").description("response message"),
                                fieldWithPath("code").description("response custom code (HttpStatus is always 200. so, code is real status code)")
                        )
                ));
    }

    @Test
    @DisplayName("같은 게시판 아이디의 포스트들 받아오기")
    void getPost() throws Exception{
        createBoard();
        RequestPost requestPost;
        Long boardId = 1L;
        for (int i = 1; i < 5; i++) {
            requestPost = new RequestPost("postTitle " + i, "writer", "password1", "content");
            this.postService.createPost(1L, requestPost);
        }
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/board/{boardId}/post", boardId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("data[0].title").value("postTitle 1"))
                .andExpect(jsonPath("data[3].title").value("postTitle 4"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post-getByBoardId",
                requestHeaders(
                        headerWithName(HttpHeaders.CONTENT_TYPE).description("request http content header")
                ),
                pathParameters(
                    parameterWithName("boardId").description("request board id")
                ),
                responseFields(
                        fieldWithPath("data").description("response data"),
                        fieldWithPath("message").description("response message"),
                        fieldWithPath("code").description("response custom code (HttpStatus is always 200. so, code is real status code)"),
                        fieldWithPath("data[].postId").description("found post writer by board id"),
                        fieldWithPath("data[].writer").description("found post writer by board id"),
                        fieldWithPath("data[].title").description("found post writer by board id"),
                        fieldWithPath("data[].content").description("found post writer by board id"),
                        fieldWithPath("data[].boardId").description("found post writer by board id")

                )
        ));

    }

    private void createBoard(){

        RequestBoard requestBoard = new RequestBoard("requestTitle");
        this.boardService.createBoard(requestBoard);
    }
}