package com.cybersoft.osahaneat.payload.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequest {
    private String title;
    private String subtitle;
    private String description;
    private boolean isFreeship;
    private String address;
    private String openDate;
}
