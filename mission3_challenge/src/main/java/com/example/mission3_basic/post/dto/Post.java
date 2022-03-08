package com.example.mission3_basic.post.dto;

import com.example.mission3_basic.board.dto.Board;
import com.example.mission3_basic.post.entity.PostEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class Post {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response{
        private Long postId;
        private String title;
        private String content;
        private Board.ResponseBoard board;

        @Builder
        public Response(PostEntity post){
            this.postId = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.board = Board.ResponseBoard.builder()
                    .board(post.getBoard())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request{
        private String title;
        private String content;
        private Long boardId;

        public Request(String title, String content, Long boardId) {
            this.title = title;
            this.content = content;
            this.boardId = boardId;
        }
    }

}
