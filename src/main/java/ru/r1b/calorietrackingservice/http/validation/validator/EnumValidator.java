package ru.r1b.calorietrackingservice.http.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.r1b.calorietrackingservice.http.validation.constraint.EnumContains;

import java.lang.reflect.InvocationTargetException;

public class EnumValidator implements ConstraintValidator<EnumContains, Object> {
    private Class enumClass;

    @Override
    public void initialize(EnumContains annotation) {
        enumClass = annotation.enumClass();
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException(enumClass + " must be enum");
        }
    }

    @Override
    public boolean isValid(Object needle, ConstraintValidatorContext context) {
        if (needle == null) {
            return true;
        }
        Enum[] values;
        try {
            values = (Enum[]) enumClass.getDeclaredMethod("values").invoke(null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        for (Enum val: values) {
            if (val.name().equals(needle) || val.equals(needle)) {
                return true;
            }
        }

        return false;
    }
}
