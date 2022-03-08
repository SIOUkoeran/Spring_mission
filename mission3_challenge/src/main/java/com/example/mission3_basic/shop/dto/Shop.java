package com.example.mission3_basic.shop.dto;

import com.example.mission3_basic.shop.entity.ShopEntity;
import com.example.mission3_basic.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Shop {

    @Getter
    @NoArgsConstructor
    public static class Request{
        private String shopName;
        private String shopDescription;


    }

    @Getter
    @NoArgsConstructor
    public static class Response{
        private Long shopId;
        private String shopName;
        private String shopDescription;
        private String ownerName;
        private Long ownerId;

        @Builder
        public Response(UserEntity user, ShopEntity shop){
            shopId = shop.getId();
            shopName = shop.getShopName();
            shopDescription = shop.getShopDescription();
            ownerName = user.getUsername();
            ownerId = user.getId();
        }
    }
}
