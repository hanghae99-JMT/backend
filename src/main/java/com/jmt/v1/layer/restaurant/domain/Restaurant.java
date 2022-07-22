package com.jmt.v1.layer.restaurant.domain;

import com.jmt.v1.layer.like.domain.Likes;
import com.jmt.v1.layer.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurant_id;
    private String name;
    private String description;
    private String category;
    private String address;
    private Long likeCount;
    private int map_x;
    private int map_y;
    private String thumbnail;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Likes> likes;
}
