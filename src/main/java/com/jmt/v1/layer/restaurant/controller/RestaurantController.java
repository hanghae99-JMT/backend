package com.jmt.v1.layer.restaurant.controller;

import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantRankingResponseDto;
import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantSearchResponseDto;
import com.jmt.v1.layer.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/api/search")
    public RestaurantSearchResponseDto getSearchResultList(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "x") String x,
            @RequestParam(value = "y") String y,
            @RequestParam(value = "page") String page
    ) {
        return restaurantService.getSearchResultList(keyword, x, y, page);
    }
}
