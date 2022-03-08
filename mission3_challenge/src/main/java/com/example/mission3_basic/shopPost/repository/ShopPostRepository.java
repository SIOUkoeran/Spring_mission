package com.example.mission3_basic.shopPost.repository;

import com.example.mission3_basic.shopPost.entity.ShopPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopPostRepository extends JpaRepository<ShopPostEntity, Long> {
}
