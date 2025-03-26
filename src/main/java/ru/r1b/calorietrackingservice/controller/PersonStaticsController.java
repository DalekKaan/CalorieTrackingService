package ru.r1b.calorietrackingservice.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.r1b.calorietrackingservice.scheme.personstatistics.CheckLimit;
import ru.r1b.calorietrackingservice.scheme.userstatistics.DailyReport;
import ru.r1b.calorietrackingservice.scheme.userstatistics.EatingHistory;
import ru.r1b.calorietrackingservice.service.PersonService;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("/person")
public class PersonStaticsController {
    private final PersonService personService;
    PersonService service;

    public PersonStaticsController(PersonService service, PersonService personService) {
        this.service = service;
        this.personService = personService;
    }

    @GetMapping("/daily-report")
    public DailyReport getDailyReport(
            @RequestParam UUID personId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return personService.getDailyReport(personId, date);
    }

    @GetMapping("/check-limit")
    public CheckLimit checkLimit(UUID userId) {
        // todo: to be implemented
        return new CheckLimit(false);
    }

    @GetMapping("/eating-history")
    public EatingHistory getEatingHistory(UUID userId) {
        // todo: to be implemented
        return new EatingHistory(Map.of());
    }
}
