package ru.r1b.calorietrackingservice.repository;

import org.springframework.data.repository.Repository;
import ru.r1b.calorietrackingservice.model.Eating;

import java.util.Optional;
import java.util.UUID;

public interface EatingRepository extends Repository<Eating, UUID> {
    Optional<Eating> findById(UUID id);
}
