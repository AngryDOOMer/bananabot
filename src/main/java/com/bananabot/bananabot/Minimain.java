package com.bananabot.bananabot;

import com.bananabot.bananabot.dto.openApi.info.allPairs.AllPairsRs;
import com.bananabot.bananabot.dto.privateApi.account.AccountRq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static void main(String[] args) throws JsonProcessingException {
        WebClient client = WebClient.builder()
                .baseUrl("https://payeer.com/api/trade/info")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        String rs = client
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();

        AllPairsRs allPairsRs = mapper.readValue(rs, AllPairsRs.class);
        System.out.println("");
    }
}