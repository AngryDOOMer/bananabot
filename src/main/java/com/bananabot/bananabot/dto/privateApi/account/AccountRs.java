package com.bananabot.bananabot.dto.privateApi.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AccountRs {

    private boolean success;

    private List<String> balances;

    private double total;

    private double available;

    private double hold;
}
