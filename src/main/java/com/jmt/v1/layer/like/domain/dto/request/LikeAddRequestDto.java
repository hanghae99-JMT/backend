package com.jmt.v1.layer.like.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor //임시
@NoArgsConstructor
@Getter
public class LikeAddRequestDto {
    private String rid;
    private String name;
    private String category;
    private String address;
    private String phone;
    private Long like;
    private String x;
    private String y;
    private String url;
}
