package com.example.mission3_basic.shop.entity;


import com.example.mission3_basic.common.BaseEntity;
import com.example.mission3_basic.review.entity.ShopReviewEntity;
import com.example.mission3_basic.shop.dto.Shop;
import com.example.mission3_basic.shopPost.entity.ShopPostEntity;
import com.example.mission3_basic.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "shop")
@NoArgsConstructor
@Getter
public class ShopEntity extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="shop_id")
    private Long id;

    @NotBlank
    private String shopName;

    private String shopDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "shop")
    private List<ShopPostEntity> shopPost = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<ShopReviewEntity> shopReview = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ShopStatus shopStatus = ShopStatus.CREATED;


    @Builder
    public ShopEntity(UserEntity user, Shop.Request request){
        this.shopName = request.getShopName();
        this.shopDescription = request.getShopDescription();
        setUser(user);
    }
    public void setUser(UserEntity user){
        this.user = user;
        user.getShops().add(this);
    }

    public void delete(){
        super.updateDeleteAt();
        this.shopStatus= ShopStatus.DELETED;
    }

    public void update(Shop.Request request){
        this.shopName = request.getShopName();
        this.shopDescription = request.getShopDescription();

    }

}
