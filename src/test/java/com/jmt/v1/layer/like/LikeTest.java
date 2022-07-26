package com.jmt.v1.layer.like;

import com.jmt.v1.layer.like.domain.dto.request.LikeAddRequestDto;
import com.jmt.v1.layer.like.domain.dto.response.LikeAddResponseDto;
import com.jmt.v1.layer.like.service.LikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LikeTest {
    private final LikeService likeService;

    @Autowired
    public LikeTest(LikeService likeService) {
        this.likeService = likeService;
    }

//    @Test
//    public void addLike() {
//        LikeAddRequestDto likeAddRequestDto = new LikeAddRequestDto("3", "식당3","한식","대구","000-0000",0L, "3","3","");
//
//        LikeAddResponseDto likeAddResponseDto = likeService.addLike(likeAddRequestDto);
//
//        System.out.println(likeAddResponseDto.getLike());
//    }
}
