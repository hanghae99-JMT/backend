package com.jmt.v1.layer.like.controller;

import com.jmt.v1.layer.like.domain.dto.response.LikeResponseDto;
import com.jmt.v1.layer.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Text;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/api/user/{id}/like")
    public List<LikeResponseDto> getMyLikes(@PathVariable("id") String userEmail) {
        return likeService.getMyLikes(userEmail);
    }
}
