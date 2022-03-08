package com.example.mission3_basic.post.service;

import com.example.mission3_basic.post.dto.Post;
import com.example.mission3_basic.user.entity.UserEntity;

import java.util.List;

public interface PostService {

    Post.Response createPost(Long boardId, Post.Request request, UserEntity user);
    Post.Response updatePost(Long postId, Post.Request request, UserEntity user);
    void deletePost(Long postId, String username);
    Post.Response getPost(Long postId);
    List<Post.Response> getPostsByBoard(Long boardId);
    List<Post.Response> getAllPosts();

}
