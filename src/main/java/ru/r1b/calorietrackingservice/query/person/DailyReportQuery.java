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
        // для ревью:
        // я знаю что можно использовать аннотацию @Query в репозитории, но я считаю что для отчётов хорошей практикой
        // будет именно использование отдельного класса для каждого конкретного запроса, так как запросы в них могут быть
        // достаточно большими и сложными, в то время как репозиторий скорее должен работать только со своей сущностью
        String sql =
                """
                    SELECT sum(d.caloric_content * (e.dishes -> (d.id::text))::int) AS caloriesReceived,
                           count(DISTINCT e.*)                                      AS eating
                    FROM eating e
                             CROSS JOIN LATERAL jsonb_object_keys(e.dishes) AS die
                             INNER JOIN dish d ON die = d.id::text
                    WHERE person_id = :personId
                      AND date(date_time) = :date
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
