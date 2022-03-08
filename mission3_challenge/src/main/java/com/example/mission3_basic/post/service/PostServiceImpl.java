package com.example.mission3_basic.post.service;

import com.example.mission3_basic.board.entity.BoardEntity;
import com.example.mission3_basic.board.service.BoardService;
import com.example.mission3_basic.board.exception.InvalidWriterException;
import com.example.mission3_basic.post.entity.PostStatus;
import com.example.mission3_basic.post.dto.Post;
import com.example.mission3_basic.post.entity.PostEntity;
import com.example.mission3_basic.post.exception.NotFoundPostException;
import com.example.mission3_basic.post.repository.PostRepository;
import com.example.mission3_basic.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final BoardService boardService;

    public PostServiceImpl(PostRepository postRepository, BoardService boardService) {
        this.postRepository = postRepository;
        this.boardService = boardService;
    }

    @Override
    public Post.Response createPost(Long boardId, Post.Request request, UserEntity user) {
        log.info("boardId {}", boardId);

        BoardEntity findBoard = this.boardService.getBoardReturnEntity(boardId);
        PostEntity post = PostEntity.builder()
                .board(findBoard)
                .request(request)
                .user(user)
                .build();

        this.postRepository.save(post);

        return Post.Response.builder()
                .post(post)
                .build();
    }

    @Override
    public Post.Response updatePost(Long postId, Post.Request request, UserEntity user) {

        PostEntity findPost = getPostReturnEntity(postId);
        if (!findPost.getUser().getId().equals(user.getId())){
            throw new InvalidWriterException();
        }
        findPost.update(request, this.boardService.getBoardReturnEntity(request.getBoardId()));
        this.postRepository.save(findPost);

        return Post.Response.builder()
                .post(findPost)
                .build();
    }


    @Override
    public void deletePost(Long postId, String username) {

        PostEntity findPost = getPostReturnEntity(postId);
        if (!Objects.equals(username, findPost.getWriter())){
            throw new InvalidWriterException();
        }
        findPost.delete();
    }

    @Override
    @Transactional(readOnly = true)
    public Post.Response getPost(Long postId) {
        PostEntity post  = getPostReturnEntity(postId);
        return Post.Response
                .builder()
                .post(post)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post.Response> getPostsByBoard(Long boardId) {

        return this.boardService.getBoardReturnEntity(boardId)
                .getPosts().stream()
                .filter(post -> post.getPostStatus() == PostStatus.CREATED)
                .map(Post.Response::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post.Response> getAllPosts() {
        return this.postRepository.findAll().stream()
                .map(Post.Response::new)
                .collect(Collectors.toList());
    }

    public PostEntity getPostReturnEntity(Long postId){
        PostEntity post =  this.postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);
        if (post.getPostStatus() == PostStatus.DELETED){
            throw new NotFoundPostException();
        }
        return post;
    }
}
