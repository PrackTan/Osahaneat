package com.cybersoft.osahaneat.payload.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequest {
    private int userId;
    private int resId;
    private int[] foodIds;
}
