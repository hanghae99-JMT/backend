package com.jmt.v1.util.SearchLocal.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchLocalResponseDto {
    private SearchLocalMeta meta;
    private List<SearchLocalDocument> documents;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class SearchLocalMeta {
        private int total_count;
        private int pageable_count;
        private boolean is_end;
        private RegionInfo same_name;

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class RegionInfo {
            private String[] region;
            private String keyword;
            private String selected_region;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class SearchLocalDocument {
        private String id;
        private String place_name;
        private String category_name;
        private String category_group_code;
        private String category_group_name;
        private String phone;
        private String address_name;
        private String road_address_name;
        private String x;
        private String y;
        private String place_url;
        private String distance;
    }

}
