package com.jmt.v1.layer.review.controller;

import com.jmt.v1.layer.review.domain.dto.request.ReviewRequestDto;
import com.jmt.v1.layer.review.domain.dto.response.ReviewResponseDto;
import com.jmt.v1.layer.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/api/user/{id}/review")
    public List<ReviewResponseDto> getMyReviews(@PathVariable("id") String userEmail) {
        return reviewService.getReviews(userEmail);
    }

    @PostMapping("/api/review")
    public void registerReview(@RequestBody ReviewRequestDto reviewRequestDto) {

    }
}
