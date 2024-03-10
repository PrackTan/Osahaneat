package com.cybersoft.osahaneat.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "image")
    private String image;
    @Column(name = "time_ship")
    private String timeShip;
    @Column(name = "price")
    private double price;
    @Column(name = "is_freeship")
    private boolean isFreeship;
    @Column(name = "description")
    private String desciption;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_id")
    private Category category;
    @OneToMany(mappedBy = "food")
    private Set<RatingFood> listRatingFood;
    @OneToMany(mappedBy = "food")
    private Set<OrderDetail> listOrderDetail;
}
