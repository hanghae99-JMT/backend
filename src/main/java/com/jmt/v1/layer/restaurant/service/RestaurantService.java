package com.jmt.v1.layer.restaurant.service;

import com.jmt.v1.layer.like.infra.LikeRepository;
import com.jmt.v1.layer.restaurant.domain.Restaurant;
import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantRankingResponseDto;
import com.jmt.v1.layer.restaurant.domain.dto.response.RestaurantSearchResponseDto;
import com.jmt.v1.layer.restaurant.infra.RestaurantRepository;
import com.jmt.v1.layer.user.domain.User;
import com.jmt.v1.api.kakao.KakaoClient;
import com.jmt.v1.api.kakao.domain.dto.SearchLocalRequestDto;
import com.jmt.v1.api.kakao.domain.dto.SearchLocalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final LikeRepository likeRepository;
    private final KakaoClient searchLocalClient;

    private int getLikeFlag(Restaurant restaurant, User user) {
        if(user == null) {
            return 0;
        }

        if(likeRepository.existsByRestaurantAndUser(restaurant, user)) {
            return 1;
        } else {
            return 0;
        }
    }

    private List<RestaurantRankingResponseDto> changeListToDtoList(List<Restaurant> restaurantList, User user) {
        List<RestaurantRankingResponseDto> restaurantRankingResponseDtoList = new ArrayList<>();

        for (Restaurant restaurant : restaurantList) {
            restaurantRankingResponseDtoList.add(new RestaurantRankingResponseDto(restaurant, getLikeFlag(restaurant, user)));
        }

        return restaurantRankingResponseDtoList;
    }

    public List<RestaurantRankingResponseDto> getRankingList(User user) {
        List<Restaurant> restaurantList = restaurantRepository.findTop10ByOrderByLikeCountDesc();

        List<RestaurantRankingResponseDto> restaurantRankingResponseDtoList = changeListToDtoList(restaurantList, user);

        return restaurantRankingResponseDtoList;
    }

    private boolean checkPage(String page) {
        int pageInt = Integer.parseInt(page);

        return pageInt > 0 && pageInt < 10;
    }

    private RestaurantSearchResponseDto makeRestaurantSearchResponseDto(User user, SearchLocalResponseDto searchLocalResponseDto) {
        int totalCount = searchLocalResponseDto.getMeta().getTotal_count();
        List<SearchLocalResponseDto.SearchLocalDocument> searchLocalDocumentList = searchLocalResponseDto.getDocuments();

        List<RestaurantSearchResponseDto.Documents> documents = new ArrayList<>();

        for(SearchLocalResponseDto.SearchLocalDocument document : searchLocalDocumentList) {
            String rid = document.getId();
            Long like = 0L;

            if(restaurantRepository.existsById(rid)) {
                like = restaurantRepository.findById(rid).get().getLikeCount();

                Restaurant restaurant = restaurantRepository.findById(rid).get();

                documents.add(new RestaurantSearchResponseDto.Documents(document, like, getLikeFlag(restaurant, user)));
            } else {
                documents.add(new RestaurantSearchResponseDto.Documents(document, like, 0));
            }
        }

        RestaurantSearchResponseDto restaurantSearchResponseDto = new RestaurantSearchResponseDto(totalCount, documents);

        return restaurantSearchResponseDto;
    }

    public RestaurantSearchResponseDto getSearchResultList(User user, String keyword, String x, String y, String page) {
        if(!checkPage(page)) {
            throw new RuntimeException("존재하지 않는 페이지입니다.");
        }

        SearchLocalRequestDto searchLocalRequestDto = new SearchLocalRequestDto(keyword, x, y, Integer.parseInt(page));
        SearchLocalResponseDto searchLocalResponseDto = searchLocalClient.searchLocal(searchLocalRequestDto);

        return makeRestaurantSearchResponseDto(user, searchLocalResponseDto);
    }
}
