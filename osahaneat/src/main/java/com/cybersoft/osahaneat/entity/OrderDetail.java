package com.cybersoft.osahaneat.entity;

import com.cybersoft.osahaneat.entity.key.KeyOrderDetail;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    KeyOrderDetail keyOrderDetail;
    @ManyToOne
    @JoinColumn(name = "order_id",insertable = false,updatable = false)
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "food_id",insertable = false,updatable = false)
    private Food food;
    @Column(name = "create_date")
    private Date createDate;
}
