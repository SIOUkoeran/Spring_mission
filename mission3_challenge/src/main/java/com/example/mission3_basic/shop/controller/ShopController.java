package com.example.mission3_basic.shop.controller;


import com.example.mission3_basic.common.response.Response;
import com.example.mission3_basic.common.response.ResponseObject;
import com.example.mission3_basic.shop.service.ShopService;
import com.example.mission3_basic.shop.dto.Shop;
import com.example.mission3_basic.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;
    private final UserService userService;

    public ShopController(ShopService shopService, UserService userService) {
        this.shopService = shopService;
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Response createShop(@AuthenticationPrincipal User user, @RequestBody Shop.Request request){

        Shop.Response response = this.shopService.createShop(this.userService.getUserReturnEntity(user.getUsername()), request);

        return Response.of()
                .code("2010")
                .message("CREATE_SHOP")
                .data(response)
                .build();
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject getShopsByUser(@PathVariable Long userId){
        List<Shop.Response> shops = this.shopService.getShops(this.userService.getUserByIdReturnUserEntity(userId));
        return ResponseObject.builder()
                .code("2000")
                .message("FIND_SHOPS_BY_USER")
                .data(shops)
                .build();
    }

    @PutMapping("/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateShop(@PathVariable Long shopId, @RequestBody Shop.Request request, @AuthenticationPrincipal User user){
        Shop.Response response = this.shopService.updateShop(shopId, request, user.getUsername());
        return Response.of()
                .code("2010")
                .message("UPDATE_SHOP")
                .data(response)
                .build();
    }

    @DeleteMapping("/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteShop(@PathVariable Long shopId, @AuthenticationPrincipal User user){
        this.shopService.deleteShop(shopId, user.getUsername());
        return Response.of()
                .code("2010")
                .message("DELETE_SHOP")
                .data(null)
                .build();
    }

    @GetMapping("/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public Response getShop(@PathVariable Long shopId){
        Shop.Response findShop = this.shopService.getShop(shopId);
        return Response.of()
                .code("2000")
                .message("FIND_SHOP")
                .data(findShop)
                .build();

    }

}
