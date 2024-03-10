package com.cybersoft.osahaneat.repository;

import com.cybersoft.osahaneat.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
}
