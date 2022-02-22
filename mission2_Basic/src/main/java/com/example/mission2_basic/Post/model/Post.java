package com.example.mission2_basic.Post.model;


import com.example.mission2_basic.Post.dto.RequestPost;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post {
    private Long id;
    private String title;
    private String writer;
    private String password;
    private String content;
    private Long boardId;
    private LocalDateTime createdAt = LocalDateTime.now();


    public Post(Long boardId, RequestPost requestPost){
        this.title = requestPost.getTitle();
        this.writer = requestPost.getWriter();
        this.password = requestPost.getPassword();
        this.content = requestPost.getContent();
        this.boardId = boardId;
    }
    public Post(Long postId, Post post){
        this.id = postId;
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.password = post.getPassword();
        this.content = post.getContent();
        this.boardId = post.getBoardId();
    }

    public Post(RequestPost requestPost){
        this.id = requestPost.getId();
        this.title = requestPost.getTitle();
        this.writer = requestPost.getWriter();
        this.password = requestPost.getPassword();
        this.content = requestPost.getContent();
        this.boardId = requestPost.getBoardId();
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
