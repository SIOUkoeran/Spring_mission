package com.example.mission3_basic.board.entity;

import com.example.mission3_basic.common.BaseEntity;
import com.example.mission3_basic.post.entity.PostEntity;
import com.example.mission3_basic.user.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "board")
public class BoardEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @OneToMany(mappedBy = "board")
    private List<PostEntity> posts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus = BoardStatus.CREATED;

    public BoardEntity(@NonNull String title, UserEntity user) {
        this.title = title;
        this.user = user;
        this.boardStatus = BoardStatus.CREATED;
        setUser(user);
    }

    public void setUser(UserEntity user){
        this.user= user;
        user.getBoards().add(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void delete(){
        this.boardStatus = BoardStatus.DELETED;
    }
}
