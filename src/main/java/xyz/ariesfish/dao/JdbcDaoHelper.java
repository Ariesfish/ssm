package xyz.ariesfish.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

// same as JdbcDaoSupport, use org.springframework.jdbc.core.support.JdbcDaoSupport
public class JdbcDaoHelper {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        if (jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate(dataSource);
        }
    }

    private JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
