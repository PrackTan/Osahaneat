package com.cybersoft.osahaneat.payload.Respone;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryReponse {
    private String name;
    private List<MenuReponse> menuReponseList;
}
