package com.example.mission3_basic.review.entity;


import com.example.mission3_basic.common.BaseEntity;
import com.example.mission3_basic.review.dto.Review;
import com.example.mission3_basic.shop.entity.ShopEntity;
import com.example.mission3_basic.shopPost.entity.ShopPostEntity;
import com.example.mission3_basic.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "shop_review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopReviewEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity writer;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @ManyToOne
    @JoinColumn(name ="shop_post_id")
    private ShopPostEntity post;


    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus = ReviewStatus.CREATED;


    @Builder
    public ShopReviewEntity(Review.Request request, UserEntity userEntity, ShopPostEntity shopPost){
        this.title = request.getReviewTitle();
        this.review = request.getReviewContent();
        setUser(userEntity);
        setShop(shop);
        setShopPost(shopPost);
    }

    public void setShopPost(ShopPostEntity shopPost){
        this.post = shopPost;
        shopPost.getShopReviews().add(this);
    }

    public void setUser(UserEntity user){
        this.writer = user;
        user.getReviews().add(this);
    }

    public void setShop(ShopEntity shop){
        this.shop = shop;
        shop.getShopReview().add(this);
    }

    public void delete(){
        reviewStatus = ReviewStatus.DELETED;
        super.updateDeleteAt();
    }

    public void update(Review.Request request){
        this.title = request.getReviewTitle();
        this.review = request.getReviewContent();
    }
}
