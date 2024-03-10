package com.cybersoft.osahaneat.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;
    @Column(name = "create_date")
    private Date createDate;
    @OneToMany(mappedBy = "orders")
    private Set<OrderDetail> listOrderDetail;
}
