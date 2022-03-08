package com.example.mission3_basic.shopPost.service;


import com.example.mission3_basic.shopPost.dto.ShopPost;
import com.example.mission3_basic.shopPost.entity.ShopPostEntity;
import com.example.mission3_basic.user.entity.UserEntity;

import java.util.List;

public interface ShopPostService {
    ShopPost.Response createShopPost(UserEntity user, ShopPost.Request request);
    ShopPost.Response updateShopPost(UserEntity user, ShopPost.Request request, Long shopPostId);
    void deleteShopPost(UserEntity user, Long shopId, Long shopPostId);
    List<ShopPost.Response> getShopPostByShop(Long ShopId);
    ShopPost.Response getShopPost(Long ShopPostId);
    ShopPostEntity getShopReturnEntity(Long ShopPostId);
}
