package com.cybersoft.osahaneat.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rating_food")
public class RatingFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
    @Column(name = "content")
    private String content;
    @Column(name = "rate_point")
    private int ratePoint;
}
