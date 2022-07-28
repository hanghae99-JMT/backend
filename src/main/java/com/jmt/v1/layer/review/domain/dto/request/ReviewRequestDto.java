package com.jmt.v1.layer.review.domain.dto.request;

import lombok.Getter;

@Getter
public class ReviewRequestDto {
    private String rid;
    private String text;
    private String name;
    private String category;
    private String address;
    private String phone;
    private Long like;
    private String x;
    private String y;
    private String url;
}
