package com.example.mission2_basic.Board.Model;

import com.example.mission2_basic.Board.Dto.RequestBoard;
import com.example.mission2_basic.Post.model.Post;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Board {

    private Long boardId;
    @NonNull
    private String title;

    private HashSet<Long> posts = new HashSet<>();

    public Board(Long boardId, Board requestBoard) {
        this.boardId = boardId;
        this.title = requestBoard.getTitle();
    }

    public Board(RequestBoard requestBoard){
        this.title = requestBoard.getTitle();
    }


    public void addPost(Post post){
        if (!posts.contains(post.getId())){
            this.posts.add(post.getId());
        }
    }
    public void deletePost(Post post){
        posts.remove(post.getId());
    }
    public void update(RequestBoard requestBoard){
        this.title= requestBoard.getTitle();
    }
    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", title='" + title + '\'' +
                ", posts=" + posts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (boardId != null ? !boardId.equals(board.boardId) : board.boardId != null) return false;
        if (!title.equals(board.title)) return false;
        return posts != null ? posts.equals(board.posts) : board.posts == null;
    }

    @Override
    public int hashCode() {
        int result = boardId != null ? boardId.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + (posts != null ? posts.hashCode() : 0);
        return result;
    }
}
