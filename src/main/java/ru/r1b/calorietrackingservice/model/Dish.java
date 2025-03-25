package ru.r1b.calorietrackingservice.model;

import java.util.UUID;

public class Dish implements DataModel {
    private UUID id;
    private String title;
    private int caloricContent;
    private int proteins;
    private int fats;
    private int carbs;

    @Override
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCaloricContent() {
        return caloricContent;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }

    public int getCarbs() {
        return carbs;
    }
}
