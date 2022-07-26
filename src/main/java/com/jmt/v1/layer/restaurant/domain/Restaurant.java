package com.jmt.v1.layer.restaurant.domain;

import com.jmt.v1.layer.like.domain.dto.request.LikeAddRequestDto;
import com.jmt.v1.layer.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Restaurant {
    @Id
    private String restaurant_id;
    private String name;
    private String category;
    private String address;
    private String phone;
    private Long likeCount;
    private String map_x;
    private String map_y;
    private String url;
    private String thumbnail;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Review> reviews;

    public Restaurant(LikeAddRequestDto likeAddRequestDto) {
        this.restaurant_id = likeAddRequestDto.getRid();
        this.name = likeAddRequestDto.getName();
        this.category = likeAddRequestDto.getCategory();
        this.address = likeAddRequestDto.getAddress();
        this.phone = likeAddRequestDto.getPhone();
        this.likeCount = likeAddRequestDto.getLike();
        this.map_x = likeAddRequestDto.getX();
        this.map_y = likeAddRequestDto.getY();
        this.url = likeAddRequestDto.getUrl();
    }

    public Long increaseLikeCount() {
        this.likeCount++;

        return likeCount;
    }
}
