package com.jmt.v1.layer.restaurant;

import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantRankingResponseDto;
import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantSearchResponseDto;
import com.jmt.v1.layer.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RestaurantTest {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantTest(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Test
    public void getRankingList() {
        List<RestaurantRankingResponseDto> restaurantRankingResponseDtoList = restaurantService.getRankingList();

        for(int i=0; i<restaurantRankingResponseDtoList.size(); i++) {
            System.out.println(restaurantRankingResponseDtoList.get(i).getName());
        }
    }

    @Test
    public void getSearchResultList() {
        RestaurantSearchResponseDto restaurantSearchResponseDto = restaurantService.getSearchResultList("갈비집", "1", "1", "1");

        for(int i=0; i<restaurantSearchResponseDto.getSearchResult().size(); i++) {
            System.out.println(restaurantSearchResponseDto.getSearchResult().get(i).getName());
        }
    }
}
