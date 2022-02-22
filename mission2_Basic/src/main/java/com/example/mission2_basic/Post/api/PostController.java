package com.example.mission2_basic.Post.api;

import com.example.mission2_basic.Post.dto.ResponsePost;
import com.example.mission2_basic.Post.dto.RequestPost;
import com.example.mission2_basic.Post.model.Post;
import com.example.mission2_basic.commonResponse.Response;
import com.example.mission2_basic.Post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/board/{boardId}/post/create")
    @ResponseStatus(HttpStatus.OK)
    public Response createPost(@Validated @RequestBody RequestPost requestPost, @PathVariable Long boardId){
        Post post = this.postService.createPost(boardId, requestPost);
        ResponsePost response = new ResponsePost(post);
        return new Response("2010", "CREATE_POST", response);
    }

    @GetMapping("posts")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllPosts(){
        return new Response("2000", "POSTS", this.postService.getAllBoards());
    }

    @GetMapping("/board/{boardId}/post")
    @ResponseStatus(HttpStatus.OK)
    public Response getPostsByBoard(@PathVariable Long boardId){
        return new Response("2000", "POSTS_BY_BOARD", this.postService.getPosts(boardId));
    }

    @PutMapping("post/update")
    @ResponseStatus(HttpStatus.OK)
    public Response updatePost(@RequestBody RequestPost post){
        return new Response("2000", "UPDATE_POST", this.postService.update(post));
    }

    @DeleteMapping("post/delete")
    @ResponseStatus(HttpStatus.OK)
    public Response deletePost(@RequestBody  RequestPost post){
        System.out.println(post.toString());
        this.postService.deletePost(post);
        return new Response("2000", "DELETE_POST");
    }


}
