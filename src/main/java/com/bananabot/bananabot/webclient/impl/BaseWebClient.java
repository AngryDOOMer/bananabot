package com.bananabot.bananabot.webclient.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BaseWebClient {

    @Value("${payeer.webclient.baseUrl}")
    String baseUrl;
        protected WebClient.Builder baseWebClientBuild(){
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl);
    }
}
