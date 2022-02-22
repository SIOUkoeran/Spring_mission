package com.example.mission2_basic.Post.repository;

import com.example.mission2_basic.Board.Model.Board;
import com.example.mission2_basic.Board.repository.BoardRepository;
import com.example.mission2_basic.Post.dto.RequestPost;
import com.example.mission2_basic.Post.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostRepository {
    private final static HashMap<Long, Post> postStored = new HashMap<>();
    private static Long postId = 0L;

    private final BoardRepository boardRepository;

    public Post save(Post post) {
        if (post.getId() == null){
            Post tempPost = new Post(++postId, post);
            postStored.put(postId, tempPost);
            saveBoard(post.getBoardId(), tempPost);

            return postStored.get(tempPost.getId());
        }else{
            postStored.replace(post.getId(),post);
            return postStored.get(post.getId());
        }
    }

    private Board saveBoard(Long boardId, Post post){
        Board findBoard = findBoard(boardId);
        findBoard.addPost(post);
        return findBoard;
    }
    private Board findBoard(Long boardId){
        return this.boardRepository.findById(boardId);
    }
    public List<Post> findAll(){
        return new ArrayList<>(postStored.values());
    }

    public Post findById(Long postId){
        return postStored.get(postId);
    }

    public void deletePost(RequestPost post){
        Post findPost = this.findById(post.getId());
        Board findBoard = findBoard(findPost.getBoardId());
        if (post.getPassword().equals(findPost.getPassword())){
            findBoard.deletePost(findPost);
            postStored.remove(post.getId());
        }else{
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }
    }
    public void deleteAll(){
        postStored.clear();
        postId = 0L;
    }

}
