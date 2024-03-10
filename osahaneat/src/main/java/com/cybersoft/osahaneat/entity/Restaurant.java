package com.cybersoft.osahaneat.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "is_freeship")
    private boolean isFreeShip;

    @Column(name = "address")
    private String address;

    @Column(name = "open_date")
    private Date openDate;

    @OneToMany(mappedBy = "restaurant")
    private Set<RatingRestaurant> listRatingRestaurants;

    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> listOrders;

    @OneToMany(mappedBy = "restaurant")
    private Set<MenuRestaurant> listMenuRestaurants;

    @OneToMany(mappedBy = "restaurant")
    private Set<Promo> listPromos;
}
