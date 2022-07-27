package com.jmt.v1.layer.like.service;

import com.jmt.v1.layer.like.domain.Likes;
import com.jmt.v1.layer.like.domain.dto.request.LikeAddRequestDto;
import com.jmt.v1.layer.like.domain.dto.response.LikeAddResponseDto;
import com.jmt.v1.layer.like.domain.dto.response.LikeResponseDto;
import com.jmt.v1.layer.like.infra.LikeRepository;
import com.jmt.v1.layer.restaurant.domain.Restaurant;
import com.jmt.v1.layer.restaurant.infra.RestaurantRepository;
import com.jmt.v1.layer.user.domain.User;
import com.jmt.v1.layer.user.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeAddResponseDto addLike(User user, LikeAddRequestDto likeAddRequestDto) {
        String rid = likeAddRequestDto.getRid();

        Restaurant restaurant;

        if(restaurantRepository.existsById(rid)) {
            restaurant = restaurantRepository.findById(rid).get();
        } else {
            restaurant = restaurantRepository.save(new Restaurant(likeAddRequestDto));
        }

        Likes like = likeRepository.save(new Likes(user, restaurant));

        Long likeCount = restaurant.increaseLikeCount();

        LikeAddResponseDto likeAddResponseDto = new LikeAddResponseDto(likeCount);

        return likeAddResponseDto;
    }

    //유저 객체를 이메일로 받아와서 그 객체를 넘겨줘야함
    public List<LikeResponseDto> getMyLikes(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                ()-> new NullPointerException("이메일이 없습니다."));
        List<Likes> likes = likeRepository.findAllByUser(user);

        List<LikeResponseDto> likeResponseDtoList = new ArrayList<>();

        for (Likes like : likes) {
            likeResponseDtoList.add(
                    new LikeResponseDto(
                            like.getRestaurant().getRestaurant_id(), like.getRestaurant().getName(), like.getRestaurant().getCategory(),
                            like.getRestaurant().getAddress(), like.getRestaurant().getPhone(),
                            like.getRestaurant().getLikeCount(), like.getRestaurant().getMap_x()
                            , like.getRestaurant().getMap_y(), like.getRestaurant().getUrl(),1)
            );
        }
        return likeResponseDtoList;
    }
}
