package com.cybersoft.osahaneat.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRequest {
    private String title;
    private String timeShip;
    private boolean isFreeship;
    private String price;
    private int cateId;

}
