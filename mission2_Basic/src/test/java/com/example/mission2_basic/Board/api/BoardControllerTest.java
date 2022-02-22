package com.example.mission2_basic.Board.api;

import com.example.mission2_basic.Board.Dto.RequestBoard;
import com.example.mission2_basic.Board.Service.BoardService;
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

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BoardService boardService;

    @BeforeEach
    void setUp(){
        boardService.deleteAllBoards();
    }

    @Test
    @DisplayName("게시판 생성")
    void createBoardApi() throws Exception {
        RequestBoard requestBoard = new RequestBoard("sampleBoard");
        mockMvc.perform(post("/api/board/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBoard)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").exists())
                .andExpect(jsonPath("code").value("2010"))
                .andExpect(jsonPath("data.title").value("sampleBoard"))
                .andDo(document("board-create",
                        requestHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("http content header")
                        ),
                        requestFields(
                                fieldWithPath("title").description("board title")
                        ),
                        responseFields(
                                fieldWithPath("data").description("response data"),
                                fieldWithPath("message").description("response message"),
                                fieldWithPath("code").description("response custom code (HttpStatus is always 200. so, code is real status code)"),
                                fieldWithPath("data.title").description("created board title"),
                                fieldWithPath("data.boardId").description("created board id"),
                                fieldWithPath("data.posts").description("created board post id list")
                        )
                ))
        ;
    }

    @Test
    @DisplayName("게시판 수정")
    void updateBoard() throws Exception{
        RequestBoard requestBoard = new RequestBoard("title");
        boardService.createBoard(requestBoard);
        RequestBoard requestBoard1 = new RequestBoard("updateTitle");
        mockMvc.perform(put("/api/board/update/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBoard1)))
                .andExpect(jsonPath("data.title").value("updateTitle"))
                .andExpect(jsonPath("message").value("UPDATE_BOARD"))
                .andDo(print())
                .andDo(document("board-update",
                        requestHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("reqeust http content header")
                        ),
                        requestFields(
                                fieldWithPath("title").description("board title")
                        ),
                        responseFields(
                                fieldWithPath("data").description("response data"),
                                fieldWithPath("message").description("response message"),
                                fieldWithPath("code").description("response custom code (HttpStatus is always 200. so, code is real status code)"),
                                fieldWithPath("data.title").description("revised board title"),
                                fieldWithPath("data.boardId").description("revised board id"),
                                fieldWithPath("data.posts").description("revised board post id list")
                        )
                ))

        ;
    }

    @Test
    @DisplayName("게시판 삭제")
    void deleteBoard() throws Exception{
        RequestBoard requestBoard = new RequestBoard("title");
        boardService.createBoard(requestBoard);
        mockMvc.perform(delete("/api/board/delete/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("DELETE_BOARD"))
                .andDo(document("board-delete",
                        responseFields(
                                fieldWithPath("data").description("response data"),
                                fieldWithPath("message").description("response message"),
                                fieldWithPath("code").description("response custom code (HttpStatus is always 200. so, code is real status code)")
                        )
                ))

        ;
    }

    @Test
    @DisplayName("게시판 불러오기")
    void getBoard() throws Exception{
        RequestBoard requestBoard = new RequestBoard("title");
        boardService.createBoard(requestBoard);
        Long boardId = 1L;
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/board/{boardId}",boardId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message").value("GET_BOARD"))
                .andExpect(jsonPath("code").value("2000"))
                .andExpect(jsonPath("data.title").value("title"))
                .andExpect(jsonPath("data.boardId").value(1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-find",
                        requestHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("reqeust http content header")
                        ),
                        pathParameters(
                                parameterWithName("boardId").description("id of the post you want to find")
                        ),
                        responseFields(
                                fieldWithPath("data").description("response data"),
                                fieldWithPath("message").description("response message"),
                                fieldWithPath("code").description("response custom code (HttpStatus is always 200. so, code is real status code)"),
                                fieldWithPath("data.title").description("board title"),
                                fieldWithPath("data.boardId").description("board id"),
                                fieldWithPath("data.posts").description("board post id list")
                        )
                ))
        ;
    }

}