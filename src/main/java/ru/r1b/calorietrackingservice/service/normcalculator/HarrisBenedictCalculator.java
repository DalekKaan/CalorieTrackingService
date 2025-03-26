package ru.r1b.calorietrackingservice.service.normcalculator;

import org.springframework.stereotype.Component;
import ru.r1b.calorietrackingservice.enumerate.Gender;
import ru.r1b.calorietrackingservice.model.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Harris-Benedict calorie calculator
 * <p>
 * <a href="https://www.rlsnet.ru/med-calculators/gastroenterologiya-i-gepatologiya/formula-xarrisa-benedikta-51">Formula version used</a>
 * </p>
 */
@Component("harris-benedict")
public class HarrisBenedictCalculator implements Calculator {

    private static int getAge(Person person) {
        return (int) ChronoUnit.YEARS.between(person.getDayOfBorn(), LocalDate.now());
    }

    @Override
    public int calculate(Person person) {
        double result;
        if (person.getGender() == Gender.MALE) {
            result = 66.473 + 13.752 * person.getWeight() + 5.003 * person.getHeight() - 6.755 * getAge(person);
        } else {
            result = 655.096 + 9.563 * person.getWeight() + 1.85 * person.getHeight() - 4.679 * getAge(person);
        }
        double k;
        switch (person.getActivity()) {
            case SITTING -> k = 1.2;
            case WEEK13 -> k = 1.375;
            case WEEK35 -> k = 1.4625;
            case WEEK45 -> k = 1.55;
            case EVERYDAY -> k = 1.6375;
            case DAY2 -> k = 1.725;
            case DAY2INTENSIVE -> k = 1.9;
            default -> k = 1;
        }
        return (int) (result * k);
    }
}
