package com.jmt.v1.layer.review.controller;

import com.jmt.v1.layer.review.domain.dto.request.ReviewRequestDto;
import com.jmt.v1.layer.review.domain.dto.response.MyReviewResponseDto;
import com.jmt.v1.layer.review.domain.dto.response.ReviewResponseDto;
import com.jmt.v1.layer.review.service.ReviewService;
import com.jmt.v1.layer.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //내 리뷰 보기
    @GetMapping("/api/user/{id}/review")
    public List<MyReviewResponseDto> getMyReviews(@PathVariable("id") String userEmail) {
        return reviewService.getMyReviews(userEmail);
    }

    // 가게 리뷰 보기
    @CrossOrigin("*")
    @GetMapping("/api/review/{id}")
    public List<ReviewResponseDto> getReviews(@RequestParam(name="rid", required = false, defaultValue = "") String rid) {
        return reviewService.getRestaurantReviews(rid);
    }

    // 가게 리뷰 작성
    @PostMapping("/api/review")
    public void registerReview(@RequestBody ReviewRequestDto reviewRequestDto, @AuthenticationPrincipal User user) {
        reviewService.registerReview(reviewRequestDto,user);
    }
}
