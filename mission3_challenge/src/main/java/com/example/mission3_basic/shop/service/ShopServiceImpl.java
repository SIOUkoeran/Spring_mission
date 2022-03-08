package com.example.mission3_basic.shop.service;


import com.example.mission3_basic.Area.exception.NotFoundAreaException;
import com.example.mission3_basic.board.exception.InvalidWriterException;
import com.example.mission3_basic.shop.entity.ShopStatus;
import com.example.mission3_basic.shop.dto.Shop;
import com.example.mission3_basic.shop.entity.ShopEntity;
import com.example.mission3_basic.shop.exception.NotFoundShopException;
import com.example.mission3_basic.shop.repository.ShopRepository;
import com.example.mission3_basic.user.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop.Response createShop(UserEntity userReturnEntity, Shop.Request request) {
        ShopEntity shop = ShopEntity.builder()
                .request(request)
                .user(userReturnEntity)
                .build();
        this.shopRepository.save(shop);
        return Shop.Response
                .builder()
                .shop(shop)
                .user(userReturnEntity)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shop.Response> getShops(UserEntity user) {

        return this.shopRepository.searchShopEntitiesByUserId(user.getId())
                .stream().filter(shop -> shop.getShopStatus() == ShopStatus.CREATED)
                .map(shop -> Shop.Response.builder()
                        .shop(shop)
                        .user(user)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Shop.Response getShop(Long shopId) {
        ShopEntity shop = getShopReturnEntity(shopId);
        return Shop.Response.builder()
                .shop(shop)
                .user(shop.getUser())
                .build();
    }

    @Override
    public ShopEntity getShopReturnEntity(Long shopId){
        ShopEntity shop = this.shopRepository.findById(shopId)
                .orElseThrow(NotFoundShopException::new);
        if (shop.getShopStatus() == ShopStatus.DELETED){
            throw new NotFoundAreaException();
        }
        return shop;
    }



    @Override
    public Shop.Response updateShop(Long shopId, Shop.Request request, String username) {


        ShopEntity findShop = getShopReturnEntity(shopId);

        if (!username.equals(findShop.getUser().getUsername())){
            throw new InvalidWriterException();
        }

        findShop.update(request);
        ShopEntity updatedShop = this.shopRepository.save(findShop);


        return Shop.Response.builder()
                .shop(updatedShop)
                .user(updatedShop.getUser())
                .build();
    }

    @Override
    public void deleteShop(Long shopId, String username) {
        ShopEntity findShop = getShopReturnEntity(shopId);
        if (!username.equals(findShop.getUser().getUsername())){
            throw new InvalidWriterException();
        }
        findShop.delete();
    }
}
