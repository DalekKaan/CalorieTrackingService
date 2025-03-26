package ru.r1b.calorietrackingservice.service;

import org.springframework.stereotype.Service;
import ru.r1b.calorietrackingservice.model.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PersonService {
    public int getAge(Person person) {
        return (int) ChronoUnit.YEARS.between(person.getDayOfBorn(), LocalDate.now());
    }
}
