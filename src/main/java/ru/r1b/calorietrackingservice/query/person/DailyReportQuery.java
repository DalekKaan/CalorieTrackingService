package ru.r1b.calorietrackingservice.query.person;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.r1b.calorietrackingservice.http.scheme.personstatistics.DailyReport;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public class DailyReportQuery {
    NamedParameterJdbcTemplate jdbcTemplate;

    public DailyReportQuery(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DailyReport getData(UUID personId, LocalDate date) {
        String sql =
                """
                    SELECT sum(d.caloric_content * die.cnt) AS caloriesReceived, count(DISTINCT e.*) AS eating
                    FROM eating e
                             INNER JOIN dish_in_eating die ON e.id = die.eating_id
                             INNER JOIN dish d on die.dish_id = d.id
                    WHERE person_id = :personId\s
                      AND date(date_time) = :date\s
                    GROUP BY person_id, date(date_time)
                """;
        var result = jdbcTemplate.query(
                sql,
                new MapSqlParameterSource()
                        .addValue("personId", personId)
                        .addValue("date", date),
                new BeanPropertyRowMapper<>(DailyReport.class)
        );

        return result.isEmpty() ? null : result.get(0);
    }
}
