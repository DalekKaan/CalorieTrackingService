package ru.r1b.calorietrackingservice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.r1b.calorietrackingservice.constraint.MinYears;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MinYearsValidator implements ConstraintValidator<MinYears, LocalDate> {

    int minYears;

    @Override
    public void initialize(MinYears constraintAnnotation) {
        minYears = constraintAnnotation.minYears();
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        return (int) ChronoUnit.YEARS.between(date, LocalDate.now()) >= minYears;
    }
}