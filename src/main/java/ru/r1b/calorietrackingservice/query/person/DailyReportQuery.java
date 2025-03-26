package ru.r1b.calorietrackingservice.query.person;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.r1b.calorietrackingservice.scheme.userstatistics.DailyReport;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public class DailyReportQuery {
    JdbcTemplate jdbcTemplate;

    public DailyReportQuery(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DailyReport getData(UUID personId, LocalDate date) {
        // todo: use named params
        String sql = "SELECT sum(d.caloric_content) AS caloriesReceived, count(DISTINCT e.*) AS eating\n" +
                "FROM eating e\n" +
                "         INNER JOIN dish_in_eating die ON e.id = die.eating_id\n" +
                "         INNER JOIN dish d on die.dish_id = d.id\n" +
                "WHERE person_id = ? \n" +
                "  AND date_time BETWEEN ? AND ? \n" +
                "GROUP BY person_id, date(date_time)";
        var result = jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    double caloriesReceived = resultSet.getDouble("caloriesReceived");
                    int eating = resultSet.getInt("eating");
                    return new DailyReport(caloriesReceived, eating);
                },
                personId,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay()
        );

        return result.isEmpty() ? null : result.get(0);
    }
}
