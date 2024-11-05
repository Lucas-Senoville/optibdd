package com.exo1.exo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskPerProjectService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createMaterializedView() {
        String createView = """
        CREATE MATERIALIZED VIEW IF NOT EXISTS task_per_project AS
        SELECT
            p.projet_id AS project_id,
            p.name AS project_name,
            COUNT(t.task_id) AS task_count
        FROM
            projet p
        LEFT JOIN
            task t ON p.projet_id = t.projet_id
        GROUP BY
            p.projet_id, p.name;
    """;
        jdbcTemplate.execute(createView);
    }

    public void refreshMaterializedView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW task_per_project");
    }
}
