package com.cybersoft.osahaneat.repository;

import com.cybersoft.osahaneat.entity.RatingRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRestaurantRepository extends JpaRepository<RatingRestaurant, Integer> {
}
