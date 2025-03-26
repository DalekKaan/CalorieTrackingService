package ru.r1b.calorietrackingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.r1b.calorietrackingservice.model.Eating;
import ru.r1b.calorietrackingservice.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EatingRepository extends JpaRepository<Eating, UUID> {
    @Query("""
            SELECT e FROM Eating e WHERE e.person = ?1 AND date(e.dateTime) = ?2
            """)
    List<Eating> findByPersonAndDate(Person person, LocalDate date);

    List<Eating> findAllByPerson(Person person);
}
