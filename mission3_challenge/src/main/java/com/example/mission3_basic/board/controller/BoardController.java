package com.example.mission3_basic.board.controller;

import com.example.mission3_basic.board.service.BoardService;
import com.example.mission3_basic.board.dto.Board;
import com.example.mission3_basic.common.response.Response;
import com.example.mission3_basic.user.entity.UserEntity;
import com.example.mission3_basic.user.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public Response createBoard(@AuthenticationPrincipal User user, @RequestBody Board.RequestBoard board){
        UserEntity findUser = this.userService.getUserReturnEntity(user.getUsername());

        return Response.of()
                .code("2010")
                .message("BOARD_CREATE")
                .data(this.boardService.createBoard(board, findUser))
                .build();
    }

    @GetMapping("/{boardId}")
    public Response findBoard(@PathVariable Long boardId){
        return Response.of()
                .code("2000")
                .message("FIND_BOARD")
                .data(this.boardService.getBoard(boardId))
                .build();
    }

    @GetMapping("/boards")
    public Response findAllBoard(){
        return Response.builder()
                .code("2000")
                .message("FIND_BOARDS")
                .data(Collections.singletonList(this.boardService.getBoards()))
                .build();
    }

    @PutMapping("/{boardId}")
    public Response updateBoard(@AuthenticationPrincipal User user , @PathVariable Long boardId,
                                @RequestBody Board.RequestBoard requestBoard){
        return Response.of()
                .code("2010")
                .message("UPDATE_BOARD")
                .data(this.boardService.updateBoard(boardId,requestBoard,
                        this.userService.getUserReturnEntity(user.getUsername())))
                .build();

    }

    @DeleteMapping("/{boardId}")
    public Response deleteBoard(@AuthenticationPrincipal User user, @PathVariable Long boardId){
        this.boardService.deleteBoard(boardId, this.userService.getUserReturnEntity(user.getUsername()));
        return Response.of()
                .code("2000")
                .message("DELETE_BOARD")
                .data(List.of())
                .build();
    }

}
