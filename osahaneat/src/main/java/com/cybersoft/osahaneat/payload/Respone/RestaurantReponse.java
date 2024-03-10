package com.cybersoft.osahaneat.payload.Respone;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantReponse {
    private int id;
    private String image;
    private String title;
    private double rating;
    private String subtitle;
    private boolean isFreeShip;
    private String openDate;
    private String description;
    List<CategoryReponse> categoryReponsesList;
}
