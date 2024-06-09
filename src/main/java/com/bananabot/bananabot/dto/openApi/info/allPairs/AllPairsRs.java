package com.bananabot.bananabot.dto.openApi.info.allPairs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
public class AllPairsRs {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("limits")
    private Limits limits;

    @JsonProperty("pairs")
    private Map<String, PairInfo> pairs;

    @Data
    public static class Limits {
        @JsonProperty("requests")
        private List<Request> requests;

        @JsonProperty("weights")
        private List<Weights> weights;

        @JsonProperty("orders")
        private List<Order> orders;
    }

    @Data
    public static class Request {
        @JsonProperty("interval")
        private String interval;

        @JsonProperty("interval_num")
        private int intervalNum;

        @JsonProperty("limit")
        private int limit;
    }

    @Data
    public static class Weights {
        @JsonProperty("interval")
        private String interval;

        @JsonProperty("interval_num")
        private int intervalNum;

        @JsonProperty("limit")
        private int limit;
    }

    @Data
    public static class Order {
        @JsonProperty("interval")
        private String interval;

        @JsonProperty("interval_num")
        private int intervalNum;

        @JsonProperty("limit")
        private int limit;
    }

    @Data
    public static class PairInfo {
        @JsonProperty("price_prec")
        private int pricePrec;

        @JsonProperty("min_price")
        private String minPrice;

        @JsonProperty("max_price")
        private String maxPrice;

        @JsonProperty("min_amount")
        private double minAmount;

        @JsonProperty("min_value")
        private double minValue;

        @JsonProperty("fee_maker_percent")
        private double feeMakerPercent;

        @JsonProperty("fee_taker_percent")
        private double feeTakerPercent;

        @JsonProperty("amount_prec")
        private int amountPrec;

        @JsonProperty("value_prec")
        private int valuePrec;
    }
}