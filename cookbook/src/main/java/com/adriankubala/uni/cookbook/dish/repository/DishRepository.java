package com.adriankubala.uni.cookbook.dish.repository;

import com.adriankubala.uni.cookbook.dish.repository.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
