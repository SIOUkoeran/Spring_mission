package com.example.mission3_basic.review.repository;

import com.example.mission3_basic.review.entity.ShopReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<ShopReviewEntity, Long> {
    List<ShopReviewEntity> findShopReviewEntitiesByShop(Long shopId);
}
