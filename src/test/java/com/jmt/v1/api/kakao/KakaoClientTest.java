package com.jmt.v1.api.kakao;

import com.jmt.v1.api.kakao.domain.dto.SearchLocalRequestDto;
import com.jmt.v1.api.kakao.domain.dto.SearchLocalResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KakaoClientTest {
    private final KakaoClient searchLocalClient;

    @Autowired
    public KakaoClientTest(KakaoClient searchLocalClient) {
        this.searchLocalClient = searchLocalClient;
    }

    @Test
    public void searchLocalTest() {
        SearchLocalRequestDto searchLocalRequestDto = new SearchLocalRequestDto("칼국수", "127.7265155", "37.8853782", 11);

        SearchLocalResponseDto result = searchLocalClient.searchLocal(searchLocalRequestDto);

        System.out.println("result: " + result);
    }
}
