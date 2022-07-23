package com.jmt.v1.layer.review.service;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
import com.jmt.v1.layer.restaurant.infra.RestaurantRepository;
import com.jmt.v1.layer.review.domain.Review;
import com.jmt.v1.layer.review.domain.dto.request.ReviewRequestDto;
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

    @Transactional
    public void registerReview(ReviewRequestDto reviewRequestDto, String restaurantId, Long userId) {
        User user = userRepository.findById(userId).get();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        Review review = new Review(reviewRequestDto,user,restaurant);
    }

    public List<ReviewResponseDto> getReviews(String userEmail) {
        User user = userRepository.findByEmail(userEmail).get();
        List<Review> reviews = reviewRepository.findAllByUser(user);

        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();

        for(int i=0; i < reviews.size(); i++) {
            reviewResponseDtoList.add(new ReviewResponseDto(reviews.get(i).getRestaurant().getName(),
                    reviews.get(i).getText()));
        }
        return reviewResponseDtoList;
    }
}
