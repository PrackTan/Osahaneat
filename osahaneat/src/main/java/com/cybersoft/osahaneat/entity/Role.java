package com.cybersoft.osahaneat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "create_date")
    private Date createDate;
    @OneToMany(mappedBy = "roles")
    private Set<Users> listUser;
}
