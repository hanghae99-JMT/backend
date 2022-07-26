package com.jmt.v1.util.SearchLocal;

import com.jmt.v1.util.SearchLocal.domain.dto.SearchLocalRequestDto;
import com.jmt.v1.util.SearchLocal.domain.dto.SearchLocalResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchLocalClientTest {
    private final SearchLocalClient searchLocalClient;

    @Autowired
    public SearchLocalClientTest(SearchLocalClient searchLocalClient) {
        this.searchLocalClient = searchLocalClient;
    }

    @Test
    public void searchLocalTest() {
        SearchLocalRequestDto searchLocalRequestDto = new SearchLocalRequestDto("갈비집", "127.06283102249932", "37.514322572335935", 1);

        SearchLocalResponseDto result = searchLocalClient.searchLocal(searchLocalRequestDto);

        System.out.println("result: " + result);
    }
}
