package ru.r1b.calorietrackingservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.r1b.calorietrackingservice.http.validation.constraint.EnumContains;
import ru.r1b.calorietrackingservice.http.validation.constraint.MinYears;
import ru.r1b.calorietrackingservice.enumerate.Gender;
import ru.r1b.calorietrackingservice.enumerate.PhysicalActivity;
import ru.r1b.calorietrackingservice.enumerate.Purpose;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Person implements ResourceEntity {
    // для ревью:
    // я знаю что здесь для сообщений об ошибках следовало использовать файлы локализации,
    // но в рамках тестового задания реши обойтись прямым указанием сообщения
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Person name is required")
    private String name;

    @NotBlank(message = "Person email is required")
    @Email(message = "Person email has wrong format")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Person day of born is required")
    @Past(message = "Person day of born can't exceed today")
    @MinYears(minYears = 5, message = "Person must be at least {minYears} years old")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dayOfBorn;

    @NotNull(message = "Person gender is required")
    @EnumContains(enumClass = Gender.class, message = "Person gender must be `MALE` or `FEMALE`")
    private Gender gender;

    /**
     * Person weight (kg)
     */
    @NotNull(message = "Person weight is required")
    @Min(value = 20, message = "Person weight mint be grater than {value}")
    @Max(value = 250, message = "Person weight mint be lower than {value}")
    private int weight;

    /**
     * Person height (sm)
     */
    @NotNull(message = "Person height is required")
    @Min(value = 20, message = "Person height mint be grater than {value}")
    @Max(value = 250, message = "Person height mint be lower than {value}")
    private int height;

    @NotNull(message = "Person purpose is required")
    @EnumContains(enumClass = Purpose.class, message = "Person purpose must be `LOSS`, `GAIN` or `MAINT`")
    private Purpose purpose;

    @NotNull(message = "Person physical activity purpose is required")
    @EnumContains(enumClass = PhysicalActivity.class, message = "Person purpose must be `NONE`, `SITTING`, `WEEK13`, `WEEK35`, `WEEK45`, `EVERYDAY`, `DAY2` or `DAY2INTENSIVE`")
    private PhysicalActivity activity;

    public Person() {

    }

    public Person(String name, String email, LocalDate dayOfBorn, Gender gender, int weight, int height, Purpose purpose, PhysicalActivity activity) {
        // для ревью:
        // данный конструктор использован только для тестирования, но для самого приложения он не создаёт никаких проблем,
        // поэтому я решил его оставить чтобы не использовать сеттеры или рефлексию
        this.name = name;
        this.email = email;
        this.dayOfBorn = dayOfBorn;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.purpose = purpose;
        this.activity = activity;
    }

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

    public PhysicalActivity getActivity() {
        return activity;
    }
}
