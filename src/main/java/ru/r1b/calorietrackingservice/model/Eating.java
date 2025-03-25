package ru.r1b.calorietrackingservice.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Eating implements DataModel {
    private UUID id;
    private User user;
    private LocalDateTime dateTime;
    private HashMap<Integer, Dish> dishes;

    @Override
    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HashMap<Integer, Dish> getDishes() {
        return dishes;
    }
}
