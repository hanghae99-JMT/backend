package com.jmt.v1.layer.like.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class LikeResponseDto {
    private String rid;
    private String name;
    private String category;
    private String address;
    private String phone;
    private Long like;
    private String x;
    private String y;
    private String url;
    private Integer like_flag;
}
