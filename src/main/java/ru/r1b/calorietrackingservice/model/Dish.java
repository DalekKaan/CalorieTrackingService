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
    // для ревью:
    // я знаю что здесь для сообщений об ошибках следовало использовать файлы локализации,
    // но в рамках тестового задания реши обойтись прямым указанием сообщения

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Dish title is required")
    private String title;
    @Positive

    // для ревью:
    // я не уверен, что знаю максимально возможные значения, поэтому решил ограничиться потолком числа integer
    @Positive(message = "Caloric content must be a positive value")
    @Max(value = Integer.MAX_VALUE, message = "Caloric content must not exceed " + Integer.MAX_VALUE)
    private int caloricContent;

    // для ревью:
    // хорошая идея хранить дробные числа с известной точностью в целых, домножая и деля их на нужную точность,
    // что позволит безопасно выполнять промежуточные вычисления, но тогда желательно иметь таблицу множителей
    // для разных величин с датами изменения и я тоже не стал это делать в рамках тестового задания
    @Positive(message = "Proteins must be a positive value")
    @Max(value = 100, message = "Proteins must not exceed " + Integer.MAX_VALUE)
    private int proteins;

    @Positive(message = "Fats must be a positive value")
    @Max(value = 100, message = "Fats must not exceed " + Integer.MAX_VALUE)
    private int fats;

    @Positive(message = "Cabs must be a positive value")
    @Max(value = 100, message = "Cabs must not exceed " + Integer.MAX_VALUE)
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
