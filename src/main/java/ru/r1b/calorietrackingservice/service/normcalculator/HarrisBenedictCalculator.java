package ru.r1b.calorietrackingservice.service.normcalculator;

import ru.r1b.calorietrackingservice.enumerate.Gender;
import ru.r1b.calorietrackingservice.model.User;
import ru.r1b.calorietrackingservice.service.UserService;

/**
 * Harris-Benedict calorie calculator
 */
public class HarrisBenedictCalculator implements Calculator {
    private final UserService userService;

    public HarrisBenedictCalculator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public int calculate(User user) {
        if (user.getGender() == Gender.MALE) {
            return (int) (88.36 + 13.4 * user.getWeight() + 4.8 * user.getHeight() - 5.7 * userService.getAge(user));
        }
        return (int) (447.6 + 9.2 * user.getWeight() + 3.1 * user.getHeight() - 4.3 * userService.getAge(user));
    }
}
