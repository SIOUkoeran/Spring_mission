package com.example.mission3_basic.review.controller;

import com.example.mission3_basic.common.response.Response;
import com.example.mission3_basic.common.response.ResponseObject;
import com.example.mission3_basic.review.dto.Review;
import com.example.mission3_basic.review.service.ReviewService;
import com.example.mission3_basic.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/create/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public Response createReview(Review.Request request, @AuthenticationPrincipal User user, @PathVariable Long shopId){
        Review.Response response = this.reviewService.createReview(this.userService.getUserReturnEntity(user.getUsername()), request,shopId);
        return Response.of()
                .code("2010")
                .message("CREATE_REVIEW")
                .data(response)
                .build();
    }

    @GetMapping("/shop/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseObject getReviewByShop(@PathVariable Long shopId){
        List<Review.Response> responses = this.reviewService.getReviewsByShop(shopId);
        return ResponseObject.builder()
                .code("2000")
                .message("FIND_REVIEWS_BY_SHOP")
                .data(responses)
                .build();
    }

    @GetMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public Response getReview(@PathVariable Long reviewId) {
        Review.Response response = this.reviewService.getReview(reviewId);
        return Response.of()
                .code("2000")
                .message("FIND_REVIEW")
                .data(response)
                .build();
    }

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateReview(@PathVariable Long reviewId, @RequestBody Review.Request request, @AuthenticationPrincipal User user){
        Review.Response response = this.reviewService.updateReview(request,reviewId,this.userService.getUserReturnEntity(user.getUsername()));
        return Response.of()
                .code("2010")
                .message("UPDATE_REVIEW")
                .data(response)
                .build();
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal User user){
        this.reviewService.deleteReview(reviewId, user.getUsername());
        return Response.of()
                .code("2010")
                .message("DELETE_REVIEW")
                .data(List.of())
                .build();
    }


}
