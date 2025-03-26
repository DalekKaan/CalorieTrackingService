package ru.r1b.calorietrackingservice.enumerate;

public enum PhysicalActivity {
    NONE("none"),
    SITTING("sitting"),
    WEEK13("week_1_3"),
    WEEK35("week_3_5"),
    WEEK45("week_4_5"),
    EVERYDAY("every_day"),
    DAY2("day_2"),
    DAY2INTENSIVE("day_2_intensive"),

    ;
    public String val;

    PhysicalActivity(String val) {
        this.val = val;
    }
}
