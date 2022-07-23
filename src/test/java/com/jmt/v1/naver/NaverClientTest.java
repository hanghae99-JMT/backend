package com.jmt.v1.naver;

import com.jmt.v1.naver.domain.dto.SearchLocalRequestDto;
import com.jmt.v1.naver.domain.dto.SearchLocalResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {
    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest() {
        SearchLocalRequestDto searchLocalRequestDto = new SearchLocalRequestDto("갈비집", 5, 1, "random");
        SearchLocalRequestDto searchLocalRequestDto2 = new SearchLocalRequestDto("갈비집", 5, 1, "random");

        SearchLocalResponseDto result = naverClient.searchLocal(searchLocalRequestDto);
        SearchLocalResponseDto result2 = naverClient.searchLocal(searchLocalRequestDto2);

        System.out.println("result: " + result);
        System.out.println("result2: " + result2);
    }
}
