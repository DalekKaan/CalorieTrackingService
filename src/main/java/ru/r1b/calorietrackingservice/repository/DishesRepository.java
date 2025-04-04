package ru.r1b.calorietrackingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.r1b.calorietrackingservice.model.Dish;

import java.util.UUID;

public interface DishesRepository extends JpaRepository<Dish, UUID> {
}
