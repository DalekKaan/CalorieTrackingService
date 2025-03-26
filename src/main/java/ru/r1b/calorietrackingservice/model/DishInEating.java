package ru.r1b.calorietrackingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DishInEating {
    @ManyToOne
    @Id
    // todo: cascade
    private Dish dish;
    @ManyToOne
    @Id
    // todo: cascade
    private Eating eating;
    int cnt;
}
