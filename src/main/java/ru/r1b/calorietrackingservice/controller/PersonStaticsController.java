package ru.r1b.calorietrackingservice.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.r1b.calorietrackingservice.repository.PersonsRepository;
import ru.r1b.calorietrackingservice.scheme.personstatistics.CheckLimit;
import ru.r1b.calorietrackingservice.scheme.userstatistics.DailyReport;
import ru.r1b.calorietrackingservice.scheme.userstatistics.EatingHistory;
import ru.r1b.calorietrackingservice.service.PersonService;
import ru.r1b.calorietrackingservice.service.normcalculator.HarrisBenedictCalculator;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("/person")
// todo: validation
public class PersonStaticsController {
    private final PersonService service;
    private final PersonsRepository repository;
    private final HarrisBenedictCalculator calculator;

    public PersonStaticsController(PersonService personService, PersonsRepository repository, HarrisBenedictCalculator calculator) {
        this.service = personService;
        this.repository = repository;
        this.calculator = calculator;
    }

    @GetMapping("/daily-report")
    public DailyReport getDailyReport(
            @RequestParam UUID personId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return service.getDailyReport(personId, date);
    }

    @GetMapping("/check-limit")
    public CheckLimit checkLimit(
            @RequestParam UUID personId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getCheckLimit(personId, date);
    }

    @GetMapping("/eating-history")
    public EatingHistory getEatingHistory(UUID userId) {
        // todo: to be implemented
        return new EatingHistory(Map.of());
    }
}
