package com.example.mission2_basic.Post.dto;


import com.example.mission2_basic.Post.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ResponsePost implements Serializable {
    private String title;
    private String writer;
    private String content;
    private Long postId;
    private Long boardId;

    public ResponsePost(Post post){
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.postId = post.getId();
        this.boardId = post.getBoardId();
    }
}
