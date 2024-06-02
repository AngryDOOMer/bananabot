package com.bananabot.bananabot.dto.openApi.time;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Time {

    private boolean success;

    private String time;
}
