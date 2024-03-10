package com.cybersoft.osahaneat.payload.Respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReponse {
    private int id;

    private String username;

    private String password;

    private String fullName;

    private Date createDate;

    private RoleReponse roleReponse;
}
