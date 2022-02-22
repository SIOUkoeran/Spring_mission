package com.example.mission2_basic.Post.service;

import com.example.mission2_basic.Board.repository.BoardRepository;
import com.example.mission2_basic.Post.dto.ResponsePost;
import com.example.mission2_basic.Post.repository.PostRepository;
import com.example.mission2_basic.Post.dto.RequestPost;
import com.example.mission2_basic.Post.model.Post;
import com.example.mission2_basic.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public Post createPost(Long boardId, RequestPost requestPost){
        Post post = new Post(boardId, requestPost);
        return postRepository.save(post);
    }

    public List<ResponsePost> getAllBoards(){
        List<Post> posts = this.postRepository.findAll();
        return posts.stream()
                .map(ResponsePost::new)
                .collect(Collectors.toList());
    }


    public Post update(RequestPost requestPost) {
        Post post = new Post(requestPost);
        System.out.println(post);
        return postRepository.save(post);
    }

    public Post getPost(Long postId) {
        Post findPost = postRepository.findById(postId);
        if (findPost == null){
            throw new NotFoundException("요청하신 post가 존재하지 않습니다.");
        }
        return findPost;
    }

    public void deletePost(RequestPost post){
        postRepository.deletePost(post);
    }
    public void deleteAll(){
        postRepository.deleteAll();
    }

    public List<ResponsePost> getPosts(Long boardId) {
        HashSet<Long> postIds = boardRepository.findById(boardId).getPosts();
        System.out.println(Arrays.toString(postIds.toArray()));
        return postIds.stream().map(this.postRepository::findById)
                .map(ResponsePost::new)
                .collect(Collectors.toList());

    }
}
