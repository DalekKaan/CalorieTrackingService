package ru.r1b.calorietrackingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@Entity
public class Dish implements ResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank(message = "Dish title is required")
    private String title;
    @Positive
    @Max(value = Integer.MAX_VALUE)
    private int caloricContent;
    // todo: switch lang
    @Positive
    @Max(value = Integer.MAX_VALUE)
    private int proteins;
    @Positive
    @Max(value = Integer.MAX_VALUE)
    private int fats;
    @Positive
    @Max(value = Integer.MAX_VALUE)
    private int carbs;

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
