package org.example.dao;

import org.example.dbhelper.ConnectionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

@Testcontainers
public abstract class AbstractDaoTest {

    @Container
    static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres:16")
                    .withDatabaseName("test_db")
                    .withUsername("postgres")
                    .withPassword("postgres");

    @BeforeAll
    static void setup() {
        Properties props = new Properties();
        props.setProperty("db.url", postgres.getJdbcUrl());
        props.setProperty("db.user", postgres.getUsername());
        props.setProperty("db.password", postgres.getPassword());

        ConnectionFactory.initFactory(props);

        initDatabase();
    }

    private static void initDatabase() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            executeSqlFromFile(conn, "schema.sql");
            executeSqlFromFile(conn, "test_data.sql");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static void executeSqlFromFile(Connection conn, String fileName) throws Exception {
        try (InputStream is = AbstractDaoTest.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (is == null) {
                throw new RuntimeException(fileName + " not found");
            }

            String sql = new String(is.readAllBytes());

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
        }
    }
}
