package com.jmt.v1.layer.restaurant.domain;

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
    private String description;
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
}
