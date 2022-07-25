package com.jmt.v1.layer.review.infra;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
import com.jmt.v1.layer.review.domain.Review;
import com.jmt.v1.layer.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByUser(User user);

    List<Review> findAllByRestaurant(Restaurant restaurant);
}
