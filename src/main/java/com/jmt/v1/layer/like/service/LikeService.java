package com.jmt.v1.layer.like.service;

import com.jmt.v1.layer.like.domain.Likes;
import com.jmt.v1.layer.like.domain.dto.response.LikeResponseDto;
import com.jmt.v1.layer.like.infra.LikeRepository;
import com.jmt.v1.layer.user.domain.User;
import com.jmt.v1.layer.user.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    //유저 객체를 이메일로 받아와서 그 객체를 넘겨줘야함
    public List<LikeResponseDto> getMyLikes(String userEmail) {
        User user = userRepository.findByEmail(userEmail).get();
        List<Likes> likes = likeRepository.findAllByUser(user);

        List<LikeResponseDto> likeResponseDtoList = new ArrayList<>();

        for (Likes like : likes) {
            likeResponseDtoList.add(
                    new LikeResponseDto(like.getRestaurant().getRestaurant_id(), like.getRestaurant().getName(), like.getRestaurant().getCategory(),
                            like.getRestaurant().getDescription(), like.getRestaurant().getAddress(), like.getRestaurant().getPhone(),
                            like.getRestaurant().getLikeCount(), like.getRestaurant().getMap_x()
                            , like.getRestaurant().getMap_y(), like.getRestaurant().getUrl())
            );
        }
        return likeResponseDtoList;
    }
}
