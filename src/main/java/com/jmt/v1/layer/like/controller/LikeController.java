package com.jmt.v1.layer.like.controller;

import com.jmt.v1.layer.like.domain.dto.request.LikeAddRequestDto;
import com.jmt.v1.layer.like.domain.dto.response.LikeAddResponseDto;
import com.jmt.v1.layer.like.domain.dto.response.LikeResponseDto;
import com.jmt.v1.layer.like.service.LikeService;
import com.jmt.v1.layer.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Text;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/api/like")
    public LikeAddResponseDto addLike(@AuthenticationPrincipal User user, @RequestBody LikeAddRequestDto likeAddRequestDto) {
        return likeService.addLike(user, likeAddRequestDto);
    }

    @GetMapping("/api/user/{id}/likes")
    public List<LikeResponseDto> getMyLikes(@PathVariable("id") String userEmail) {
        return likeService.getMyLikes(userEmail);
    }
}
