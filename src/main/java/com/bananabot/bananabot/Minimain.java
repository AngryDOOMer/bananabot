package com.bananabot.bananabot;

import com.bananabot.bananabot.dto.privateApi.account.AccountRq;
import com.squareup.okhttp.*;
import lombok.SneakyThrows;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Minimain {
    @SneakyThrows
    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.now();

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.format("{\r\n    ts : \"%s\"\r\n}", String.valueOf(zdt.toInstant().toEpochMilli())));
        Request request = new Request.Builder()
                .url("https://payeer.com/api/trade/account")
                .method("POST", body)
                .addHeader("API-ID", "733f1abe-21e0-4b07-998a-3e5f4baaa197")
                .addHeader("API-SIGN", "YeS39zaPnvdF0sc2")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        System.out.println("");
    }
}