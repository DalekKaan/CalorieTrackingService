package ru.r1b.calorietrackingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DishInEating {
    @ManyToOne
    @Id
    private Dish dish;
    @ManyToOne
    @Id
    private Eating eating;

    public Dish getDish() {
        return dish;
    }

    public Eating getEating() {
        return eating;
    }
}
