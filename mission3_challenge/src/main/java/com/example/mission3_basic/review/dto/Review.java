package com.example.mission3_basic.review.dto;

import com.example.mission3_basic.review.entity.ShopReviewEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Review {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response{
        private String shopName;
        private String reviewTitle;
        private String reviewContent;
        private String writer;

        @Builder
        public Response(ShopReviewEntity shopReviewEntity){
            this.shopName = shopReviewEntity.getShop().getShopName();
            this.reviewTitle = shopReviewEntity.getTitle();
            this.reviewContent = shopReviewEntity.getReview();
            this.writer = shopReviewEntity.getWriter().getUsername();
        }
    }
    @Getter
    public static class Request{
        private String reviewTitle;
        private String reviewContent;

    }
}
