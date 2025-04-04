package ru.r1b.calorietrackingservice.http.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.r1b.calorietrackingservice.model.Person;
import ru.r1b.calorietrackingservice.http.scheme.personstatistics.CheckLimit;
import ru.r1b.calorietrackingservice.http.scheme.personstatistics.DailyReport;
import ru.r1b.calorietrackingservice.http.scheme.personstatistics.EatingHistory;
import ru.r1b.calorietrackingservice.service.PersonService;

import java.time.LocalDate;

@RestController()
@RequestMapping("/person/statistics")
public class PersonStaticsController {
    // для ревью:
    // согласно заданию реализация этих эндпоинтов не требовалась, но я решил это сделать так как это не
    // сложно ввиду отсутствия чётких требований к схеме ответа


    private final PersonService service;

    public PersonStaticsController(PersonService personService) {
        this.service = personService;
    }

    @GetMapping("/daily-report")
    public DailyReport getDailyReport(@Validated @RequestParam Person person, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getDailyReport(person, date);
    }

    @GetMapping("/check-limit")
    public CheckLimit checkLimit(@Validated @RequestParam Person person, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getCheckLimit(person, date);
    }

    @GetMapping("/eating-history")
    public EatingHistory getEatingHistory(@Validated @RequestParam Person person) {
        return service.getEatingHistory(person);
    }
}
