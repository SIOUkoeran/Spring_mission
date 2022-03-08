package com.example.mission3_basic.review.service;

import com.example.mission3_basic.review.dto.Review;
import com.example.mission3_basic.user.entity.UserEntity;

import java.util.List;

public interface ReviewService {

    Review.Response createReview(UserEntity user, Review.Request request, Long shopPostId);
    Review.Response getReview(Long reviewId);
    List<Review.Response> getReviewsByShop(Long shopPostId);
    List<Review.Response> getReviewsByUserId(Long userId);
    Review.Response updateReview(Review.Request request, Long reviewId, UserEntity user);
    void deleteReview(Long reviewId, String username);
}
