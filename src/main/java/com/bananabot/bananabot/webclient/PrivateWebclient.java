package com.bananabot.bananabot.webclient;

import com.bananabot.bananabot.dto.privateApi.account.AccountRq;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.ZonedDateTime;

@Component
public class PrivateWebclient {

    @SneakyThrows
    public void call() {
        WebClient client = WebClient.builder()
                .baseUrl("https://payeer.com/api/trade/account")
                .build();

        ZonedDateTime zdt = ZonedDateTime.now();
        AccountRq balance = new AccountRq(zdt.toInstant().toEpochMilli());

        JsonMapper jm = new JsonMapper();

        String body = jm.writeValueAsString(balance);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec("0VIrpZD75mBUiPPC".getBytes(), "HmacSHA256");
        hmac.init(secretKey);
        hmac.update(("account" + body).getBytes());
        String sign = bytesToHex(hmac.doFinal());

        String balanceRs = client
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .header("API-ID", "1bc1c6b0-3d29-4560-a417-9ae35c9ce00e")
                .header("API-SIGN", sign)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("123");

    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}