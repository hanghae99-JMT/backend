package com.jmt.v1.layer.review.service;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
import com.jmt.v1.layer.restaurant.infra.RestaurantRepository;
import com.jmt.v1.layer.review.domain.Review;
import com.jmt.v1.layer.review.domain.dto.request.ReviewRequestDto;
import com.jmt.v1.layer.review.domain.dto.response.MyReviewResponseDto;
import com.jmt.v1.layer.review.domain.dto.response.ReviewResponseDto;
import com.jmt.v1.layer.review.infra.ReviewRepository;
import com.jmt.v1.layer.user.domain.User;
import com.jmt.v1.layer.user.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;


    // 내가 작성한 리뷰 보기
    public List<MyReviewResponseDto> getMyReviews(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NullPointerException("작성한 리뷰가 없습니다.")
        );

        List<Review> reviews = reviewRepository.findAllByUser(user);

        List<MyReviewResponseDto> myReviewResponseDtoList = new ArrayList<>();

        for (Review review : reviews) {
            myReviewResponseDtoList.add(new MyReviewResponseDto(review.getRestaurant().getName(),
                    review.getText()));
        }
        return myReviewResponseDtoList;
    }

    // 레스토랑 리뷰 보기
    public List<ReviewResponseDto> getRestaurantReviews(String rid) {
        Restaurant restaurant = restaurantRepository.findById(rid).orElseThrow(
                () -> new NullPointerException("레스토랑이 없습니다."));

        List<Review> reviews = reviewRepository.findAllByRestaurant(restaurant);

        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();

        for (Review review : reviews) {
            reviewResponseDtoList.add(new ReviewResponseDto(review.getUser().getEmail(), review.getText()));
        }

        return reviewResponseDtoList;
    }

    // 레스토랑 리뷰 작성
    @Transactional
    public void registerReview(ReviewRequestDto reviewRequestDto,User user) {

        Restaurant restaurant = restaurantRepository.findById(reviewRequestDto.getRid())
                    .orElseGet(() -> new Restaurant(reviewRequestDto));

        reviewRepository.save(new Review(restaurant,user,reviewRequestDto.getText()));
    }
}
