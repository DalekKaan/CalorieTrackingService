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

    @Positive(message = "Caloric content must be a positive value")
    @Max(value = Integer.MAX_VALUE, message = "Caloric content must not exceed " + Integer.MAX_VALUE)
    private int caloricContent;

    @Positive(message = "Proteins must be a positive value")
    @Max(value = Integer.MAX_VALUE, message = "Proteins must not exceed " + Integer.MAX_VALUE)
    private int proteins;

    @Positive(message = "Fats must be a positive value")
    @Max(value = Integer.MAX_VALUE, message = "Fats must not exceed " + Integer.MAX_VALUE)
    private int fats;

    @Positive(message = "Cabs must be a positive value")
    @Max(value = Integer.MAX_VALUE, message = "Cabs must not exceed " + Integer.MAX_VALUE)
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
