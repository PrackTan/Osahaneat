package com.cybersoft.osahaneat.entity;


import com.cybersoft.osahaneat.entity.key.KeyMenuRestaurant;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "menu_restaurant")
public class MenuRestaurant {
    @EmbeddedId
    KeyMenuRestaurant keys;
    @ManyToOne
    @JoinColumn(name = "cate_id",insertable = false,updatable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "res_id",insertable = false,updatable = false)
    private Restaurant restaurant;
    @Column(name = "create_date")
    private Date createDate;

}
