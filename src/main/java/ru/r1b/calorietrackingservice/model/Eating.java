package ru.r1b.calorietrackingservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class Eating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;
    private LocalDateTime dateTime;
    @OneToMany(mappedBy = "eating")
    private Set<DishInEating> dishes;

    public void setUser(Person person) {
        this.person = person;
    }

    public UUID getId() {
        return id;
    }

    public Person getUser() {
        return person;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Set<DishInEating> getDishes() {
        return dishes;
    }
}
