package com.cybersoft.osahaneat.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseReponse {
    private int statusCode;
    private boolean isSuccess;
    private String message;
    private Object object;
}
