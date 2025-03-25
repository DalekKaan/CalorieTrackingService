package ru.r1b.calorietrackingservice.repository;

import org.springframework.data.repository.Repository;
import ru.r1b.calorietrackingservice.model.Dish;

import java.util.Optional;
import java.util.UUID;

public interface DishesRepository extends Repository<Dish, UUID> {
    Optional<Dish> findById(UUID id);
}
