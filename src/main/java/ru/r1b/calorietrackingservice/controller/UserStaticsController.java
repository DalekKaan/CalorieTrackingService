package ru.r1b.calorietrackingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.r1b.calorietrackingservice.scheme.userstatistics.CheckLimit;
import ru.r1b.calorietrackingservice.scheme.userstatistics.DailyReport;
import ru.r1b.calorietrackingservice.scheme.userstatistics.EatingHistory;

import java.util.Map;
import java.util.UUID;

@RestController("/user")
public class UserStaticsController {
    @GetMapping("/daily-report")
    public DailyReport getDailyReport(UUID userId) {
        // todo: to be implemented
        return new DailyReport(0.0,0);
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
