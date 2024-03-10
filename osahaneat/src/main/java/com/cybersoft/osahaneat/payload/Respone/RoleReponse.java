package com.cybersoft.osahaneat.payload.Respone;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleReponse {
    private int id;
    private String roleName;
    private Date createDate;
}
