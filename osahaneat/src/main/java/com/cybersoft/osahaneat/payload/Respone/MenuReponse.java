package com.cybersoft.osahaneat.payload.Respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuReponse {
    private int id;
    private String title;
    private String image;
    private boolean isFreeShip;
    private String description;
    private double price;
}
