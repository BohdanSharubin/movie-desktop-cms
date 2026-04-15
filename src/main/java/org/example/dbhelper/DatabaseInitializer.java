package org.example.dbhelper;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Scanner;

public class DatabaseInitializer {
    private static final String SCHEMA_FILE = "schema.sql";
    private static final String SQL_DATA_FILE = "data.sql";
    private final Connection connection;

    public DatabaseInitializer(Connection connection) {
        this.connection = connection;
    }

    public void init() {
        try {
           if(!isTablesExist()) {
                executeSqlScript(SCHEMA_FILE);
                executeSqlScript(SQL_DATA_FILE);
               System.out.println("Database initialization complete.");
           }  else {
               System.out.println("Database already exists.");
           }
        } catch (SQLException e) {
            throw new RuntimeException("Database initialization failed.", e);
        }

    }

    private boolean isTablesExist() throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null,
                null,
                null,
                new String[]{"TABLE"});
        return resultSet.next();
    }

    private void executeSqlScript(String fileName) throws SQLException {
        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        String sql = new Scanner(is, StandardCharsets.UTF_8)
                .useDelimiter("\\A")
                .next();
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
