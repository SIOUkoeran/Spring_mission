package com.example.mission3_basic.shop.service;

import com.example.mission3_basic.shop.dto.Shop;
import com.example.mission3_basic.shop.entity.ShopEntity;
import com.example.mission3_basic.user.entity.UserEntity;

import java.util.List;

public interface ShopService {


    Shop.Response createShop(UserEntity userReturnEntity, Shop.Request request);

    List<Shop.Response> getShops(UserEntity user);

    Shop.Response getShop(Long ShopId);

    Shop.Response updateShop(Long shopId, Shop.Request request, String username);

    void deleteShop(Long shopId, String username);
    ShopEntity getShopReturnEntity(Long shopId);

}
