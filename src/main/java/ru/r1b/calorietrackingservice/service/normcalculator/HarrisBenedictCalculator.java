package ru.r1b.calorietrackingservice.service.normcalculator;

import org.springframework.stereotype.Service;
import ru.r1b.calorietrackingservice.enumerate.Gender;
import ru.r1b.calorietrackingservice.model.Person;
import ru.r1b.calorietrackingservice.service.PersonService;

/**
 * Harris-Benedict calorie calculator
 */
@Service
public class HarrisBenedictCalculator implements Calculator {
    private final PersonService personService;

    public HarrisBenedictCalculator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public int calculate(Person person) {
        if (person.getGender() == Gender.MALE) {
            return (int) (88.36 + 13.4 * person.getWeight() + 4.8 * person.getHeight() - 5.7 * personService.getAge(person));
        }
        return (int) (447.6 + 9.2 * person.getWeight() + 3.1 * person.getHeight() - 4.3 * personService.getAge(person));
    }
}
