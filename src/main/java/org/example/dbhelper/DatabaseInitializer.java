package org.example.dbhelper;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Scanner;

/**
 * Responsible for initializing the database schema and seed data.
 * <p>
 * This class checks whether database tables exist and, if not,
 * executes SQL scripts for schema creation and initial data population.
 * </p>
 */
public class DatabaseInitializer {

    /**
     * Name of the SQL file containing database schema creation scripts.
     */
    private static final String SCHEMA_FILE = "schema.sql";

    /**
     * Name of the SQL file containing initial data insertion scripts.
     */
    private static final String SQL_DATA_FILE = "data.sql";

    /**
     * Active database connection used for initialization.
     */
    private final Connection connection;

    /**
     * Creates a new DatabaseInitializer with the given database connection.
     *
     * @param connection active database connection
     */
    public DatabaseInitializer(Connection connection) {
        this.connection = connection;
    }

    /**
     * Initializes the database if it has not been created yet.
     * <p>
     * This method checks whether any tables exist in the database.
     * If no tables are found, it executes schema and data SQL scripts.
     * Otherwise, it skips initialization.
     * </p>
     *
     * @throws RuntimeException if database initialization fails
     */
    public void init() {
        try {
            if (!isTablesExist()) {
                executeSqlScript(SCHEMA_FILE);
                executeSqlScript(SQL_DATA_FILE);
                System.out.println("Database initialization complete.");
            } else {
                System.out.println("Database already exists.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database initialization failed.", e);
        }
    }

    /**
     * Checks whether any tables exist in the connected database.
     *
     * @return true if at least one table exists, false otherwise
     * @throws SQLException if a database access error occurs
     */
    private boolean isTablesExist() throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(
                null,
                null,
                null,
                new String[]{"TABLE"}
        );
        return resultSet.next();
    }

    /**
     * Executes SQL statements from a given script file located in resources.
     *
     * @param fileName name of the SQL file in the classpath
     * @throws SQLException if SQL execution fails
     * @throws RuntimeException if the file cannot be read
     */
    private void executeSqlScript(String fileName) throws SQLException {
        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (is == null) {
            throw new RuntimeException("SQL script file not found: " + fileName);
        }

        String sql = new Scanner(is, StandardCharsets.UTF_8)
                .useDelimiter("\\A")
                .next();

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}