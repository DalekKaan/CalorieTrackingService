package ru.r1b.calorietrackingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class DishInEating {
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Id
    private Dish dish;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Id
    private Eating eating;
    int cnt;
}
