package com.jmt.v1.layer.restaurant.controller;

import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantRankingResponseDto;
import com.jmt.v1.layer.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/api/ranking")
    public List<RestaurantRankingResponseDto> getRankingList() {
        return restaurantService.getRankingList();
    }
}
