package com.cybersoft.osahaneat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_cate")
    private String nameCate;
    @Column(name = "create_date")
    private Date createdDate;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Food> listFood;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuRestaurant> listMenuRestaurants ;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getNameCate() {
//        return nameCate;
//    }
//
//    public void setNameCate(String nameCate) {
//        this.nameCate = nameCate;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public Set<Food> getListFood() {
//        return listFood;
//    }
//
//    public void setListFood(Set<Food> listFood) {
//        this.listFood = listFood;
//    }
//
//    public Set<MenuRestaurant> getListMenuRestaurants() {
//        return listMenuRestaurants;
//    }
//
//    public void setListMenuRestaurants(Set<MenuRestaurant> listMenuRestaurants) {
//        this.listMenuRestaurants = listMenuRestaurants;
//    }
}
