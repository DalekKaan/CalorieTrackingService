package ru.r1b.calorietrackingservice.service.normcalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.r1b.calorietrackingservice.enumerate.Gender;
import ru.r1b.calorietrackingservice.enumerate.PhysicalActivity;
import ru.r1b.calorietrackingservice.enumerate.Purpose;
import ru.r1b.calorietrackingservice.model.Person;
import ru.r1b.calorietrackingservice.service.PersonService;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HarrisBenedictCalculatorTest {

    @ParameterizedTest
    @MethodSource("provideUsersForCalculate")
    void calculate(Person person, int expected) {

        var calculator = new HarrisBenedictCalculator(new PersonService());
        assertEquals(expected, calculator.calculate(person));
    }

    private static Stream<Arguments> provideUsersForCalculate() {
        return Stream.of(
                Arguments.of(new Person(
                                "John Smith",
                                "john.smith@yandex.ru",
                                LocalDate.of(1993, 7, 14),
                                Gender.MALE,
                                100,
                                180,
                                Purpose.MAINT,
                                PhysicalActivity.WEEK13
                        ),
                        2932),
                Arguments.of(new Person(
                                "Olga Orlova",
                                "o.orlova@yandex.ru",
                                LocalDate.of(1966, 3, 2),
                                Gender.FEMALE,
                                70,
                                170,
                                Purpose.MAINT,
                                PhysicalActivity.SITTING
                        ),
                        1635),
                Arguments.of(new Person(
                                "Anna Petrova",
                                "a.pethova@yandex.ru",
                                LocalDate.of(1989, 2, 24),
                                Gender.FEMALE,
                                70,
                                170,
                                Purpose.MAINT,
                                PhysicalActivity.WEEK35
                        ),
                        2150)
        );
    }
}