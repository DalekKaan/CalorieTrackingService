package ru.r1b.calorietrackingservice.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.r1b.calorietrackingservice.model.Person;
import ru.r1b.calorietrackingservice.query.person.DailyReportQuery;
import ru.r1b.calorietrackingservice.scheme.userstatistics.DailyReport;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PersonService {
    DailyReportQuery dailyReportQuery;

    public PersonService(DailyReportQuery dailyReportQuery) {
        this.dailyReportQuery = dailyReportQuery;
    }

    public DailyReport getDailyReport(UUID personId, LocalDate date) {
        return dailyReportQuery.getData(personId, date);
    }

    public DailyReport getDailyReport(Person person, LocalDate date) {
        return dailyReportQuery.getData(person.getId(), date);
    }


}
