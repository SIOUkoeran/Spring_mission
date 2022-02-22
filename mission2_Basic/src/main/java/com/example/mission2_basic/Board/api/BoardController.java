package com.example.mission2_basic.Board.api;

import com.example.mission2_basic.Board.Service.BoardService;
import com.example.mission2_basic.Board.Dto.RequestBoard;
import com.example.mission2_basic.commonResponse.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/board")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Response createBoard(@RequestBody  RequestBoard requestBoard){
        return new Response("2010", "CREATE_BOARD", this.boardService.createBoard(requestBoard));
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllBoards(){
        return new Response("2000", "BOARD_LIST", this.boardService.getAllBoards());
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public Response getBoard(@PathVariable Long boardId){
        return new Response("2000", "GET_BOARD", this.boardService.getBoardById(boardId));
    }

    @PutMapping("/update/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateBoard(@PathVariable Long boardId, @RequestBody RequestBoard requestBoard){
        return new Response("2000", "UPDATE_BOARD",this.boardService.updateBoard(boardId, requestBoard));
    }

    @DeleteMapping("/delete/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteBoard(@PathVariable Long boardId){
        this.boardService.deleteBoardById(boardId);
        return new Response("2000", "DELETE_BOARD");
    }
}
