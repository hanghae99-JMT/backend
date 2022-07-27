package com.jmt.v1.util.naver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchImageRequestDto {
    // 지역 검색 요청 변수에 대한 변수 생성
    private String query;  // 검색을 원하는 문자열로서 UTF-8로 인코딩
    private int display;  // 검색 결과 출력 건수 지정(10 ~ 100)
    private int  start;  // 검색 시작 위치로 최대 1000까지 가능
    private String sort;  // 정렬 옵션: sim (유사도순), date (날짜순)
    private String filter; // 사이즈 필터 옵션: all(전체), large(큰 사이즈), medium(중간 사이즈), small(작은 사이즈)

    public MultiValueMap<String, String> toMultiValueMap() {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("sort", sort);
        map.add("filter", filter);

        return map;
    }
}
