package com.example.mission3_basic.shop.repository;


import com.example.mission3_basic.shop.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    List<ShopEntity> searchShopEntitiesByUserId(Long userId);

}
