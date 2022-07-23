package com.jmt.v1.layer.like.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeResponseDto {
    private String name;
    private String category;
    private String description;
    private String address;
    private Long like;
    private String x;
    private String y;
}
