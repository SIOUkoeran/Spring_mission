package com.example.mission3_basic.board.dto;

import com.example.mission3_basic.board.entity.BoardEntity;
import com.example.mission3_basic.post.entity.PostStatus;
import com.example.mission3_basic.post.dto.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    @Getter
    @NoArgsConstructor
    public static class RequestBoard{
        private String title;
    }


    @Getter
    @NoArgsConstructor
    public static class ResponseBoard{
        private Long boardId;
        private String title;
        private String writer;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        @Builder
        public ResponseBoard(BoardEntity board){
            this.boardId = board.getId();
            this.title = board.getTitle();
            this.writer = board.getUser().getUsername();
            createdAt = board.getCreatedAt();
            updatedAt = board.getUpdatedAt();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseList{
        private Long boardId;
        private String title;
        private String writer;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<Post.Response> posts;
        @Builder
        public ResponseList(BoardEntity board){
            this.boardId = board.getId();
            this.title = board.getTitle();
            this.writer = board.getUser().getUsername();
            createdAt = board.getCreatedAt();
            updatedAt = board.getUpdatedAt();
            this.posts = board.getPosts().stream()
                    .filter(post -> post.getPostStatus()==PostStatus.CREATED)
                    .map(post -> Post.Response.builder()
                            .post(post)
                            .build())
                    .collect(Collectors.toList());
        }
    }

}
