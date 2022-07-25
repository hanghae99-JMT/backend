package com.jmt.v1.layer.restaurant.service;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantRankingResponseDto;
import com.jmt.v1.layer.restaurant.infra.RestaurantRepository;
import com.jmt.v1.util.SearchLocal.SearchLocalClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final SearchLocalClient searchLocalClient;

    private List<RestaurantRankingResponseDto> changeListToDtoList(List<Restaurant> restaurantList) {
        List<RestaurantRankingResponseDto> restaurantRankingResponseDtoList = new ArrayList<>();

        for(int i=0; i<restaurantList.size(); i++) {
            restaurantRankingResponseDtoList.add(new RestaurantRankingResponseDto(restaurantList.get(i)));
        }

        return restaurantRankingResponseDtoList;
    }

    public List<RestaurantRankingResponseDto> getRankingList() {
        List<Restaurant> restaurantList = restaurantRepository.findTop10ByOrderByLikeCountDesc();

        List<RestaurantRankingResponseDto> restaurantRankingResponseDtoList = changeListToDtoList(restaurantList);

        return restaurantRankingResponseDtoList;
    }
}
