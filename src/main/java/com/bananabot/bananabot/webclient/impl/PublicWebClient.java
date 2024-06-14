package com.bananabot.bananabot.webclient.impl;

import com.bananabot.bananabot.dto.openApi.info.allPairs.AllPairsRs;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PublicWebClient {
    public String call(String uri){
            WebClient client = WebClient.builder()
            .baseUrl("https://payeer.com/api/trade/")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

            return client
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
    }

    public AllPairsRs callPostInfo(String currency){
        WebClient client = WebClient.builder()
                .baseUrl("https://payeer.com/api/trade/info")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client
                .post()
                .bodyValue(String.format("{\n" +
                        "    \"pair\": \"%s\"\n" +
                        "}", currency))
                .retrieve()
                .bodyToMono(AllPairsRs.class)
                .block();
    }
}
