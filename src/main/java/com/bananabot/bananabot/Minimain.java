package com.bananabot.bananabot;

import com.bananabot.bananabot.dto.openApi.info.allPairs.AllPairsRs;
import com.bananabot.bananabot.dto.privateApi.account.AccountRq;
import com.squareup.okhttp.*;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Minimain {
    @SneakyThrows
    public static void main(String[] args) {
        WebClient client = WebClient.builder()
                .baseUrl("https://payeer.com/api/trade/info")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        AllPairsRs rs = client
                .get()
                .retrieve()
                .bodyToMono(AllPairsRs.class)
                .block();
        System.out.println("");
    }
}