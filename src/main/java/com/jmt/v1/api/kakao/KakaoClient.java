package com.jmt.v1.api.kakao;

import com.jmt.v1.api.kakao.domain.dto.SearchLocalRequestDto;
import com.jmt.v1.api.kakao.domain.dto.SearchLocalResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class KakaoClient {

    @Value("${kakao.restapi.key}")
    private String kakaoRestApiKey;

    @Value("${kakao.url.search.local}")
    private String kakaoLocalSearchUrl;

    public SearchLocalResponseDto searchLocal(SearchLocalRequestDto searchLocalRequestDto) {
        URI uri = UriComponentsBuilder
                .fromUriString(kakaoLocalSearchUrl)
                .queryParams(searchLocalRequestDto.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoRestApiKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<SearchLocalResponseDto> responseType = new ParameterizedTypeReference<>(){};

        ResponseEntity<SearchLocalResponseDto> responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, responseType);

        return responseEntity.getBody();
    }
}
