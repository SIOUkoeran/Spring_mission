package com.example.mission3_basic.post.controller;


import com.example.mission3_basic.common.response.Response;
import com.example.mission3_basic.common.response.ResponseObject;
import com.example.mission3_basic.post.service.PostService;
import com.example.mission3_basic.post.dto.Post;
import com.example.mission3_basic.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Response createPost(@AuthenticationPrincipal User user, @RequestBody Post.Request request){
        Post.Response post = this.postService.createPost(request.getBoardId(), request, this.userService.getUserReturnEntity(user.getUsername()));
        return Response.of()
                .code("2010")
                .message("CREATE_POST")
                .data(post)
                .build();

    }

    @GetMapping("/board/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject getPostByBoard(@PathVariable Long boardId){
        return ResponseObject.builder()
                .code("2000")
                .message("GET_POST_BY_BOARD")
                .data(this.postService.getPostsByBoard(boardId))
                .build();

    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Response getPost(@PathVariable Long postId){
        Post.Response findPost = this.postService.getPost(postId);
        return Response.of()
                .code("2000")
                .message("FIND_POST")
                .data(findPost)
                .build();
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Response updatePost(@AuthenticationPrincipal User user, @PathVariable Long postId, @RequestBody Post.Request request){
        Post.Response response = this.postService.updatePost(postId, request, this.userService.getUserReturnEntity(user.getUsername()));
        return Response.of()
                .code("2010")
                .message("UPDATE_POST")
                .data(response)
                .build();
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deletePost(@AuthenticationPrincipal User user, @PathVariable Long postId){
        this.postService.deletePost(postId, user.getUsername());
        return Response.builder()
                .code("2010")
                .message("DELETE_POST")
                .data(List.of())
                .build();
    }

}
