package com.example.mission3_basic.review.service;


import com.example.mission3_basic.board.exception.InvalidWriterException;
import com.example.mission3_basic.review.repository.ReviewRepository;
import com.example.mission3_basic.review.entity.ReviewStatus;
import com.example.mission3_basic.review.entity.ShopReviewEntity;
import com.example.mission3_basic.review.dto.Review;
import com.example.mission3_basic.review.exception.NotFoundReviewException;
import com.example.mission3_basic.shop.entity.ShopEntity;
import com.example.mission3_basic.shopPost.entity.ShopPostEntity;
import com.example.mission3_basic.shopPost.service.ShopPostService;
import com.example.mission3_basic.user.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ShopPostService shopPostService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ShopPostService shopPostService) {
        this.reviewRepository = reviewRepository;
        this.shopPostService = shopPostService;
    }

    @Override
    public Review.Response createReview(UserEntity user, Review.Request request, Long shopPostId) {
        ShopPostEntity findShop = this.shopPostService.getShopReturnEntity(shopPostId);
        ShopReviewEntity review = ShopReviewEntity.builder()
                .shopPost(findShop)
                .userEntity(user)
                .request(request)
                .build();

        this.reviewRepository.save(review);
        return Review.Response.builder()
                .shopReviewEntity(review)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Review.Response getReview(Long reviewId) {
        ShopReviewEntity findShop = this.reviewRepository.findById(reviewId)
                .orElseThrow(NotFoundReviewException::new);
        if (findShop.getReviewStatus() == ReviewStatus.DELETED)
            throw new NotFoundReviewException();
        return Review.Response.builder()
                .shopReviewEntity(findShop)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review.Response> getReviewsByShop(Long shopId) {
        return this.reviewRepository.findShopReviewEntitiesByShop(shopId)
                .stream()
                .filter(review -> review.getReviewStatus() == ReviewStatus.CREATED)
                .map(review -> Review.Response.builder()
                        .shopReviewEntity(review)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Review.Response> getReviewsByUserId(Long userId) {
        return null;
    }

    @Override
    public Review.Response updateReview(Review.Request request, Long reviewId, UserEntity user) {
        ShopReviewEntity findReview = findReviewReturnEntity(reviewId);
        if (user.getUsername() != findReview.getWriter().getUsername()){
            throw new InvalidWriterException();
        }
        findReview.update(request);
        ShopReviewEntity updatedReview = this.reviewRepository.save(findReview);
        return Review.Response
                .builder()
                .shopReviewEntity(updatedReview)
                .build();
    }

    @Override
    public void deleteReview(Long reviewId, String username) {
        ShopReviewEntity findReview = findReviewReturnEntity(reviewId);
        if (!findReview.getWriter().getUsername().equals(username)){
            throw new InvalidWriterException();
        }
        findReview.delete();
        this.reviewRepository.save(findReview);
    }

    private ShopReviewEntity findReviewReturnEntity(Long reviewId){
        ShopReviewEntity findReview = this.reviewRepository.findById(reviewId)
                .orElseThrow(NotFoundReviewException::new);
        if (findReview.getReviewStatus() == ReviewStatus.DELETED){
            throw new NotFoundReviewException();
        }
        return findReview;
    }
}
