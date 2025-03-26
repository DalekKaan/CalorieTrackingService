package ru.r1b.calorietrackingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.r1b.calorietrackingservice.model.Eating;

import java.util.UUID;

public interface EatingRepository extends JpaRepository<Eating, UUID> {
}
