package com.jmt.v1.layer.restaurant.domain.dto.response;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RestaurantRankingResponseDto {
    private String rid;
    private String name;
    private String category;
    private String address;
    private String phone;
    private Long like;
    private String x;
    private String y;
    private String url;
    private int like_flag;

    public RestaurantRankingResponseDto(Restaurant restaurant, int like_flag) {
        this.rid = restaurant.getRestaurant_id();
        this.name = restaurant.getName();
        this.category = restaurant.getCategory();
        this.address = restaurant.getAddress();
        this.phone = restaurant.getPhone();
        this.like = restaurant.getLikeCount();
        this.x = restaurant.getMap_x();
        this.y = restaurant.getMap_y();
        this.url = restaurant.getUrl();
        this.like_flag = like_flag;
    }
}
