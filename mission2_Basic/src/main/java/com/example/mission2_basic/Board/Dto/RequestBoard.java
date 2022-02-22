package com.example.mission2_basic.Board.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestBoard {

    private String title;

    public RequestBoard(String title) {
        this.title = title;
    }
}
