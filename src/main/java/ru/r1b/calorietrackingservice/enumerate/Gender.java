package ru.r1b.calorietrackingservice.enumerate;

public enum Gender {
    MALE('m'),
    FEMALE('f');

    public char val;

    Gender(char var) {
        this.val = var;
    }
}
