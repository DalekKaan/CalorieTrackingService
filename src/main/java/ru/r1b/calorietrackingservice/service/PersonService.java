package ru.r1b.calorietrackingservice.service;

import org.springframework.stereotype.Service;
import ru.r1b.calorietrackingservice.model.Eating;
import ru.r1b.calorietrackingservice.model.Person;
import ru.r1b.calorietrackingservice.query.person.DailyReportQuery;
import ru.r1b.calorietrackingservice.repository.EatingRepository;
import ru.r1b.calorietrackingservice.repository.PersonsRepository;
import ru.r1b.calorietrackingservice.scheme.personstatistics.CheckLimit;
import ru.r1b.calorietrackingservice.scheme.userstatistics.DailyReport;
import ru.r1b.calorietrackingservice.scheme.userstatistics.EatingHistory;
import ru.r1b.calorietrackingservice.service.normcalculator.HarrisBenedictCalculator;

import java.time.LocalDate;
import java.util.*;

@Service
public class PersonService {
    private final DailyReportQuery dailyReportQuery;
    private final HarrisBenedictCalculator calculator;
    private final PersonsRepository repository;
    private final EatingRepository eatingRepository;

    public PersonService(DailyReportQuery dailyReportQuery, HarrisBenedictCalculator calculator, PersonsRepository repository, EatingRepository eatingRepository) {
        this.dailyReportQuery = dailyReportQuery;
        this.calculator = calculator;
        this.repository = repository;
        this.eatingRepository = eatingRepository;
    }

    public DailyReport getDailyReport(UUID personId, LocalDate date) {
        return dailyReportQuery.getData(personId, date);
    }

    public CheckLimit getCheckLimit(UUID personId, LocalDate date) {
        var person = repository.findById(personId);
        if (person.isEmpty()) {
            return null;
        }
        return getCheckLimit(person.get(), date);
    }

    public CheckLimit getCheckLimit(Person person, LocalDate date) {
        var dailyReport = getDailyReport(person.getId(), date);
        var norm = calculator.calculate(person);
        return new CheckLimit(dailyReport.getCaloriesReceived() > norm);

    }

    public EatingHistory getEatingHistory(UUID personId) {
        var person = repository.findById(personId);
        if (person.isEmpty()) {
            return null;
        }
        return getEatingHistory(person.get());
    }

    public EatingHistory getEatingHistory(Person person) {
        var eating = eatingRepository.findAllByPerson(person);

        Map<LocalDate, Collection<Eating>> history = new HashMap<>();
        eating.forEach(e -> {
            history.computeIfAbsent(e.getDateTime().toLocalDate(), key -> new LinkedList<>())
                    .add(e);
        });

        return new EatingHistory(history);
    }


}
