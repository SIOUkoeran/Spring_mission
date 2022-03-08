package com.example.mission3_basic.shopPost.entity;

import com.example.mission3_basic.common.BaseEntity;
import com.example.mission3_basic.review.entity.ShopReviewEntity;
import com.example.mission3_basic.shop.entity.ShopEntity;
import com.example.mission3_basic.shopPost.dto.ShopPost;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name ="shop_post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopPostEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;


    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private ShopPostStatus shopPostStatus;

    @OneToMany(mappedBy = "post")
    private List<ShopReviewEntity> shopReviews = new ArrayList<>();

    @Builder
    public ShopPostEntity(ShopPost.Request request){
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public void update(ShopPost.Request request) {
        this.content = request.getContent();
        this.title = request.getTitle();
    }

    public void delete() {
        this.shopPostStatus = ShopPostStatus.DELETE;
        super.updateDeleteAt();
    }
}
