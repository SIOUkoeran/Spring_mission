package com.example.mission3_basic.shopPost.service;

import com.example.mission3_basic.board.exception.InvalidWriterException;
import com.example.mission3_basic.shop.entity.ShopEntity;
import com.example.mission3_basic.shop.service.ShopService;
import com.example.mission3_basic.shopPost.entity.ShopPostStatus;
import com.example.mission3_basic.shopPost.dto.ShopPost;
import com.example.mission3_basic.shopPost.entity.ShopPostEntity;
import com.example.mission3_basic.shopPost.repository.ShopPostRepository;
import com.example.mission3_basic.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopPostServiceImpl implements ShopPostService{

    private final ShopPostRepository shopPostRepository;
    private final ShopService shopService;


    public ShopPostServiceImpl(ShopPostRepository shopPostRepository, ShopService shopService) {
        this.shopPostRepository = shopPostRepository;
        this.shopService = shopService;
    }

    @Override
    public ShopPost.Response createShopPost(UserEntity user, ShopPost.Request request) {
        checkOwner(user, request);
        ShopPostEntity shopPost = ShopPostEntity.builder()
                .request(request)
                .build();
        return ShopPost.Response.builder()
                .shopPost(shopPost)
                .build();
    }



    @Override
    public ShopPost.Response updateShopPost(UserEntity user, ShopPost.Request request, Long shopPostId) {
        checkOwner(user, request);
        ShopPostEntity findShopPost = this.shopPostRepository.findById(shopPostId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지않습니다."));
        findShopPost.update(request);
        ShopPostEntity updatedShopPost = this.shopPostRepository.save(findShopPost);
        return ShopPost.Response.builder()
                .shopPost(updatedShopPost)
                .build();
    }

    @Override
    public void deleteShopPost(UserEntity user, Long ShopId, Long shopPostId) {

        boolean check = user.getShops().stream()
                .anyMatch(shopEntity -> shopEntity.getId() == ShopId);
        if (!check)
            throw new InvalidWriterException();
        ShopPostEntity findShopPostEntity = this.shopPostRepository.findById(shopPostId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지않습니다."));
        findShopPostEntity.delete();
    }

    @Override
    public List<ShopPost.Response> getShopPostByShop(Long shopId) {
        ShopEntity findShopEntity = this.shopService.getShopReturnEntity(shopId);
        return findShopEntity.getShopPost().stream()
                .filter(shopPost ->  shopPost.getShopPostStatus() == ShopPostStatus.CREATE)
                .map(shopPost -> ShopPost.Response.builder()
                        .shopPost(shopPost)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ShopPost.Response getShopPost(Long shopPostId) {
        ShopPostEntity shopPost = this.shopPostRepository.findById(shopPostId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지않습니다."));
        if (shopPost.getShopPostStatus() == ShopPostStatus.DELETE)
            throw new EntityNotFoundException("존재하지 않습니다.");
        return ShopPost.Response.builder()
                .shopPost(shopPost)
                .build();
    }

    @Override
    public ShopPostEntity getShopReturnEntity(Long shopPostId) {
        ShopPostEntity find = this.shopPostRepository.findById(shopPostId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지않습니다"));
        if (find.getShopPostStatus() == ShopPostStatus.DELETE)
            throw new EntityNotFoundException("존재하지않습니다");
        return find;
    }

    private void checkOwner(UserEntity user, ShopPost.Request request) {
        boolean checkOwner = user.getShops().stream()
                .anyMatch(shop -> shop.getId() == request.getShopId());
        if (!checkOwner)
            throw new InvalidWriterException();
    }
}
