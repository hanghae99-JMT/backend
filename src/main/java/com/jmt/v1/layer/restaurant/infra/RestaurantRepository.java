package com.jmt.v1.layer.restaurant.infra;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
}
