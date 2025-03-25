package ru.r1b.calorietrackingservice.model;

import ru.r1b.calorietrackingservice.enumerate.Gender;
import ru.r1b.calorietrackingservice.enumerate.Purpose;

import java.util.Date;
import java.util.UUID;

public class User implements DataModel {
    private UUID id;
    private String name;
    private String email;
    private Date dayOfBorn;
    private Gender gender;
    private int weight;
    private int height;
    private Purpose purpose;

    @Override
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getDayOfBorn() {
        return dayOfBorn;
    }

    public Gender getGender() {
        return gender;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public Purpose getPurpose() {
        return purpose;
    }
}
