package com.jmt.v1.layer.restaurant.domain.dto.response;

import com.jmt.v1.util.SearchLocal.domain.dto.SearchLocalResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestaurantSearchResponseDto {
    private int totalCount;
    private List<Documents> searchResult;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Documents {
        private String rid;
        private String name;
        private String category;
        private String address;
        private String phone;
        private Long like;
        private String x;
        private String y;
        private String url;
        private String distance;
        private int like_flag;

        public Documents(SearchLocalResponseDto.SearchLocalDocument searchLocalDocument, Long like, int like_flag) {
            this.rid = searchLocalDocument.getId();
            this.name = searchLocalDocument.getPlace_name();
            this.category = searchLocalDocument.getCategory_name();
            this.address = searchLocalDocument.getRoad_address_name();
            this.phone = searchLocalDocument.getPhone();
            this.like = like;
            this.x = searchLocalDocument.getX();
            this.y = searchLocalDocument.getY();
            this.url = searchLocalDocument.getPlace_url();
            this.distance = searchLocalDocument.getDistance();
            this.like_flag = like_flag;
        }
    }
}
