package com.jmt.v1.layer.review.domain;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
import com.jmt.v1.layer.review.domain.dto.request.ReviewRequestDto;
import com.jmt.v1.layer.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;


    public Review(Restaurant restaurant,User user,String text) {
        this.restaurant = restaurant;
        this.user = user;
        this.text = text;
    }

}
