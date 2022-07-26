package com.jmt.v1.util.SearchLocal.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor
@Getter
public class SearchLocalRequestDto {
    private String query;
    private final String category_group_code = "FD6";
    private String x;
    private String y;
    private final int radius = 20000;
    private int page;
    private final int size = 15;
    private final String sort = "accuracy";

    public SearchLocalRequestDto(String query, String x, String y, int page) {
        this.query = query;
        this.x = x;
        this.y = y;
        this.page = page;
    }

    public MultiValueMap<String, String> toMultiValueMap() {
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query", query);
        map.add("category_group_code", category_group_code);
        map.add("x", x);
        map.add("y", y);
        map.add("radius", String.valueOf(radius));
        map.add("page", String.valueOf(page));
        map.add("size", String.valueOf(size));
        map.add("sort", sort);

        return map;
    }
}
