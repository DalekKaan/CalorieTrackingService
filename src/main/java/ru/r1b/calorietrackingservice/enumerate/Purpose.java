package ru.r1b.calorietrackingservice.enumerate;

public enum Purpose {
    LOSS("loss"),
    GAIN("gain"),
    MAINT("maint");

    public final String val;

    Purpose(String val) {
        this.val = val;
    }
}
