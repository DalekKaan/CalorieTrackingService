package ru.r1b.calorietrackingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.r1b.calorietrackingservice.model.Person;

import java.util.List;
import java.util.UUID;


public interface PersonsRepository extends JpaRepository<Person, UUID> {
}
