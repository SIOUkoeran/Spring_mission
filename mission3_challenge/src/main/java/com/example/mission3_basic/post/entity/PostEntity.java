package com.example.mission3_basic.post.entity;

import com.example.mission3_basic.board.entity.BoardEntity;
import com.example.mission3_basic.common.BaseEntity;
import com.example.mission3_basic.post.dto.Post;
import com.example.mission3_basic.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class PostEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String writer;


    @NotBlank
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus = PostStatus.CREATED;

    public void setBoard(BoardEntity board){
        this.board = board;
        board.getPosts().add(this);
    }

    public void setUser(UserEntity user){
        this.user = user;
        user.getPosts().add(this);
    }

    @Builder
    public PostEntity(Post.Request request, UserEntity user, BoardEntity board){
        this.content = request.getContent();
        this.title = request.getTitle();
        this.writer = user.getUsername();
        setUser(user);
        setBoard(board);
    }

    public void update(Post.Request request, BoardEntity board){
        setBoard(board);
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public void delete(){
        this.postStatus = PostStatus.DELETED;
        this.updateDeleteAt();
    }
}
