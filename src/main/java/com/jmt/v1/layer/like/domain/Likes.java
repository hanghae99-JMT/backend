package com.jmt.v1.layer.like.domain;

import com.jmt.v1.layer.restaurant.domain.Restaurant;
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
@Table(uniqueConstraints =
        {@UniqueConstraint(name = "UniqueRestaurantUser", columnNames = { "restaurant_id", "user_id"})})
public class Likes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Likes(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }
}
