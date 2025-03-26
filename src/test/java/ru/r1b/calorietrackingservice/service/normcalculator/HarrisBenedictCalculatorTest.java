package ru.r1b.calorietrackingservice.service.normcalculator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.r1b.calorietrackingservice.enumerate.Gender;
import ru.r1b.calorietrackingservice.enumerate.Purpose;
import ru.r1b.calorietrackingservice.model.Person;
import ru.r1b.calorietrackingservice.service.PersonService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HarrisBenedictCalculatorTest {

    @Test
    @Disabled // todo: remove when ready
    void calculate() {

        var calculator = new HarrisBenedictCalculator(new PersonService());
        // todo: use @MethodValues
        var user = new Person(
                "John Smith",
                "john.smith@yandex.ru",
                LocalDate.of(1990,1,1),
                Gender.MALE,
                100,
                180,
                Purpose.MAINT
        );
        assertEquals(2285, calculator.calculate(user));
    }
}