package ru.r1b.calorietrackingservice.query.person;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.r1b.calorietrackingservice.scheme.userstatistics.DailyReport;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Repository
public class DailyReportQuery {
    NamedParameterJdbcTemplate jdbcTemplate;

    public DailyReportQuery(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DailyReport getData(UUID personId, LocalDate date) {
        String sql = "SELECT sum(d.caloric_content) AS caloriesReceived, count(DISTINCT e.*) AS eating\n" +
                "FROM eating e\n" +
                "         INNER JOIN dish_in_eating die ON e.id = die.eating_id\n" +
                "         INNER JOIN dish d on die.dish_id = d.id\n" +
                "WHERE person_id = :personId\n" +
                "  AND date(date_time) = :date\n" +
                "GROUP BY person_id, date(date_time)";
        return jdbcTemplate.query(
                sql,
                new MapSqlParameterSource()
                        .addValue("personId", personId)
                        .addValue("date", date),
                new BeanPropertyRowMapper<DailyReport>(DailyReport.class)
        ).get(0);
    }
}
