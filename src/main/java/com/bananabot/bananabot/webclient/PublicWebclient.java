package com.bananabot.bananabot.webclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class PublicWebclient {
    public void call(){
            WebClient client = WebClient.builder()
            .baseUrl("https://payeer.com/api/trade/info")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

            String rs = client
                    .get()
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        System.out.println(rs);
    }
}
