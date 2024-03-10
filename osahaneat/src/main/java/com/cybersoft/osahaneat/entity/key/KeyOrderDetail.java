package com.cybersoft.osahaneat.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class KeyOrderDetail implements Serializable {
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "food_id")
    private int foodId;
}
