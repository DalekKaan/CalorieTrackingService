package ru.r1b.calorietrackingservice.http.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.r1b.calorietrackingservice.http.validation.validator.MinYearsValidator;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = MinYearsValidator.class)
@Documented
@Repeatable(MinYears.List.class)
public @interface MinYears {
    String message() default "the date must be at least {minYears} years in the past";
    int minYears();
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };


    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        MinYears[] value();
    }
}
