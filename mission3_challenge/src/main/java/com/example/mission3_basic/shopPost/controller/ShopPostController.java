package com.example.mission3_basic.shopPost.controller;


import com.example.mission3_basic.common.response.Response;
import com.example.mission3_basic.common.response.ResponseObject;
import com.example.mission3_basic.shopPost.service.ShopPostService;
import com.example.mission3_basic.shopPost.dto.ShopPost;
import com.example.mission3_basic.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop-post")
public class ShopPostController {

    private final ShopPostService shopPostService;
    private final UserService userService;


    public ShopPostController(ShopPostService shopPostService, UserService userService) {
        this.shopPostService = shopPostService;
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Response createShopPost(@RequestBody ShopPost.Request request, @AuthenticationPrincipal User user){
        ShopPost.Response shopPost = this.shopPostService.createShopPost(this.userService.getUserReturnEntity(user.getUsername()), request);
        return Response.of()
                .code("2010")
                .message("CREATE_SHOP_POST")
                .data(shopPost).build();
    }

    @GetMapping("/{shopPostId}")
    @ResponseStatus(HttpStatus.OK)
    public Response getShopPost(@PathVariable Long shopPostId){
        ShopPost.Response shopPost = this.shopPostService.getShopPost(shopPostId);
        return Response.of()
                .code("2000")
                .message("GET_SHOP_POST")
                .data(shopPost)
                .build();
    }

    @GetMapping("/shop/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject getShopPostsByShop(@PathVariable Long shopId){
        List<ShopPost.Response> shopPostByShop = this.shopPostService.getShopPostByShop(shopId);
        return ResponseObject.builder()
                .code("2000")
                .message("GET_SHOP_POSTS_BY_SHOP")
                .data(shopPostByShop)
                .build();

    }

    @PutMapping("/update/{shopPostId}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateShopPost(@PathVariable Long shopId, @AuthenticationPrincipal User user, @RequestBody ShopPost.Request request){
        ShopPost.Response response = this.shopPostService.updateShopPost(this.userService.getUserReturnEntity(user.getUsername()), request, shopId);
        return Response.of()
                .code("2010")
                .message("UPDATE_SHOP_POST")
                .data(response)
                .build();
    }

    @DeleteMapping("/delete/{shopId}/{shopPostId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteShopPost(@PathVariable Long shopPostId,
            @PathVariable Long shopId,@AuthenticationPrincipal User user){
        this.shopPostService.deleteShopPost(this.userService.getUserReturnEntity(user.getUsername()), shopId,shopPostId);
        return Response.of()
                .code("2010")
                .message(null)
                .build();
    }


}
