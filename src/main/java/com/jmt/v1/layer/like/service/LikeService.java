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

        for (int i = 0; i < likes.size(); i++) {
            likeResponseDtoList.add(
                    new LikeResponseDto(likes.get(i).getRestaurant().getName(), likes.get(i).getRestaurant().getCategory(),
                    likes.get(i).getRestaurant().getDescription(), likes.get(i).getRestaurant().getAddress(),
                    likes.get(i).getRestaurant().getLikeCount(), likes.get(i).getRestaurant().getMap_x()
                    , likes.get(i).getRestaurant().getMap_y())
            );
        }
        return likeResponseDtoList;
    }
}
