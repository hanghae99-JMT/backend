package com.jmt.v1.layer.restaurant.service;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantRankingResponseDto;
import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantSearchResponseDto;
import com.jmt.v1.layer.restaurant.infra.RestaurantRepository;
import com.jmt.v1.util.SearchLocal.SearchLocalClient;
import com.jmt.v1.util.SearchLocal.domain.dto.SearchLocalRequestDto;
import com.jmt.v1.util.SearchLocal.domain.dto.SearchLocalResponseDto;
import io.sentry.spring.tracing.SentrySpan;
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

    private RestaurantSearchResponseDto makeRestaurantSearchResponseDto(SearchLocalResponseDto searchLocalResponseDto) {
        int totalCount = searchLocalResponseDto.getMeta().getTotal_count();

        List<RestaurantSearchResponseDto.Documents> documents = new ArrayList<>();

        for(int i=0; i<searchLocalResponseDto.getDocuments().size(); i++) {
            String rid = searchLocalResponseDto.getDocuments().get(i).getId();
            Long like = 0L;

            if(restaurantRepository.existsById(rid)) {
                like = restaurantRepository.findById(rid).get().getLikeCount();
            }

            documents.add(new RestaurantSearchResponseDto.Documents(searchLocalResponseDto.getDocuments().get(i), like));
        }

        RestaurantSearchResponseDto restaurantSearchResponseDto = new RestaurantSearchResponseDto(totalCount, documents);

        return restaurantSearchResponseDto;
    }

    @SentrySpan
    public RestaurantSearchResponseDto getSearchResultList(String keyword, String x, String y, String page) {
        SearchLocalRequestDto searchLocalRequestDto = new SearchLocalRequestDto(keyword, x, y, Integer.parseInt(page));
        SearchLocalResponseDto searchLocalResponseDto = searchLocalClient.searchLocal(searchLocalRequestDto);

        return makeRestaurantSearchResponseDto(searchLocalResponseDto);
    }
}
