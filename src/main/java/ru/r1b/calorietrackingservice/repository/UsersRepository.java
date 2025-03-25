package ru.r1b.calorietrackingservice.repository;

import org.springframework.data.repository.Repository;
import ru.r1b.calorietrackingservice.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends Repository<User, UUID> {
    Optional<User> findById(UUID id);
}
