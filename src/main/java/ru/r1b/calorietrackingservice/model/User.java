package ru.r1b.calorietrackingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import ru.r1b.calorietrackingservice.enumerate.Gender;
import ru.r1b.calorietrackingservice.enumerate.Purpose;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class User implements DataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private LocalDate dayOfBorn;
    private Gender gender;
    private int weight;
    private int height;
    private Purpose purpose;

    public User(String name, String email, LocalDate dayOfBorn, Gender gender, int weight, int height, Purpose purpose) {
        this.name = name;
        this.email = email;
        this.dayOfBorn = dayOfBorn;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.purpose = purpose;
    }

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

    public LocalDate getDayOfBorn() {
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
