package com.jmt.v1.layer.restaurant.infra;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    List<Restaurant> findTop10ByOrderByLikeCountDesc();
}
