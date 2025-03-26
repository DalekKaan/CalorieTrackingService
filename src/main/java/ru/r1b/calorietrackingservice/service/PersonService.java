package ru.r1b.calorietrackingservice.service;

import org.springframework.stereotype.Service;
import ru.r1b.calorietrackingservice.model.Eating;
import ru.r1b.calorietrackingservice.model.Person;
import ru.r1b.calorietrackingservice.query.person.DailyReportQuery;
import ru.r1b.calorietrackingservice.repository.EatingRepository;
import ru.r1b.calorietrackingservice.scheme.personstatistics.CheckLimit;
import ru.r1b.calorietrackingservice.scheme.userstatistics.DailyReport;
import ru.r1b.calorietrackingservice.scheme.userstatistics.EatingHistory;
import ru.r1b.calorietrackingservice.service.normcalculator.HarrisBenedictCalculator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Service
public class PersonService {
    private final DailyReportQuery dailyReportQuery;
    private final HarrisBenedictCalculator calculator;
    private final EatingRepository eatingRepository;

    public PersonService(DailyReportQuery dailyReportQuery, HarrisBenedictCalculator calculator, EatingRepository eatingRepository) {
        this.dailyReportQuery = dailyReportQuery;
        this.calculator = calculator;
        this.eatingRepository = eatingRepository;
    }

    public DailyReport getDailyReport(Person person, LocalDate date) {
        var result =  dailyReportQuery.getData(person.getId(), date);
        if (result == null) {
            var out = new DailyReport();
            out.setEating(0);
            out.setCaloriesReceived(0);
            return out;
        }
        return result;
    }

    public CheckLimit getCheckLimit(Person person, LocalDate date) {
        var dailyReport = getDailyReport(person, date);
        var norm = calculator.calculate(person);
        return new CheckLimit(dailyReport.getCaloriesReceived() > norm);

    }

    public EatingHistory getEatingHistory(Person person) {
        var eating = eatingRepository.findAllByPerson(person);

        Map<LocalDate, Collection<Eating>> history = new HashMap<>();
        eating.forEach(e -> history.computeIfAbsent(e.getDateTime().toLocalDate(), key -> new LinkedList<>())
                .add(e));

        return new EatingHistory(history);
    }


}
