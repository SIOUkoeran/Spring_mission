package com.example.mission3_basic.shopPost.dto;


import com.example.mission3_basic.shopPost.entity.ShopPostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ShopPost {

    @Getter
    @NoArgsConstructor
    public static class Request{
        private String title;
        private String content;
        private Long shopId;
    }

    @Getter
    @NoArgsConstructor
    public static class Response{
        private String title;
        private String content;
        private String writer;

        @Builder
        public Response(ShopPostEntity shopPost){
            this.title = shopPost.getTitle();
            this.content = shopPost.getContent();
            this.writer = shopPost.getShop().getShopName();
        }
    }
}
