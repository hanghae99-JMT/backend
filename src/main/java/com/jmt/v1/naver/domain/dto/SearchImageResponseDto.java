package com.jmt.v1.naver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchImageResponseDto {
    private String lastBuildDate; // 검색 결과를 생성한 시간
    private int total; //  검색 결과 문서의 총 개수
    private int start; // 검색 결과 문서 중, 문서의 시작점
    private int display; // 검색된 검색 결과의 개수
    private List<SearchImageItem> items; // XML 포멧에서는 item 태그, JSON 포멧에서는 items 속성 - 개별 검색 결과이며 title, link, description, address, mapx, mapy를 포함

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class SearchImageItem{
        private String title;  // 검색 결과 업체, 기관명
        private String link;  // 검색 결과 업체, 기관의 상세 정보가 제공되는 네이버 페이지의 하이퍼텍스트 link
        private String thumbnail;  // 검색 결과 이미지의 썸네일 link
        private String sizeheight;  // 검색 결과 이미지의 썸네일 높이 (단위는 pixel)
        private String sizewidth;  // 검색 결과 이미지의 너비 (단위는 pixel)
    }
}
