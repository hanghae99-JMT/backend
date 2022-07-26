package com.jmt.v1.util.SearchLocal;

import com.jmt.v1.util.SearchLocal.domain.dto.SearchLocalRequestDto;
import com.jmt.v1.util.SearchLocal.domain.dto.SearchLocalResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class SearchLocalClient {

    @Value("${kakao.restapi.key}")
    private String kakaoRestApiKey;

    @Value("${kakao.url.search.local}")
    private String kakaoLocalSearchUrl;

    public SearchLocalResponseDto searchLocal(SearchLocalRequestDto searchLocalRequestDto) {
//    public ResponseEntity<String> searchLocal(SearchLocalRequestDto searchLocalRequestDto) {
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
//        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<SearchLocalResponseDto> responseType = new ParameterizedTypeReference<>(){};

        ResponseEntity<SearchLocalResponseDto> responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, responseType);

//        RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
//
//        ParameterizedTypeReference<SearchLocalResponseDto> responseType = new ParameterizedTypeReference<>(){};
//        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);

        return responseEntity.getBody();
    }
}
