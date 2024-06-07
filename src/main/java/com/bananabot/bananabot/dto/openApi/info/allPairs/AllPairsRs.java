package com.bananabot.bananabot.dto.openApi.info.allPairs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AllPairsRs {

    private boolean success;

    private Limits limits;

    private List<Pairs> pairs;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private class Limits {

        private Requests requests;

        private Weights weights;

        private Orders orders;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private class Pairs {
        private int price_prec;

        private double min_price;

        private double max_price;

        private double min_amount;

        private double min_value;

        private double fee_maker_percent;

        private double fee_taker_percent;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private class Requests{
        private String interval;

        private int interval_num;

        private int limit;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private class Weights{
        private String interval;

        private int interval_num;

        private int limit;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private class Orders{
        private String interval;

        private int interval_num;

        private int limit;
    }
}

