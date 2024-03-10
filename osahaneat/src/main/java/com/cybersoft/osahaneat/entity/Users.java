package com.cybersoft.osahaneat.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "create_date")
    private Date createDate;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles;
    @OneToMany(mappedBy = "users")
    private Set<RatingFood> listRatingFood;
    @OneToMany(mappedBy = "users")
    private Set<RatingRestaurant> listRatingRestaurants;
    @OneToMany(mappedBy = "users")
    private Set<Orders> listOrders;

}
