package org.example.dbhelper;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final Properties PROPERTIES = new Properties();
    private static final PGSimpleDataSource DATA_SOURCE = new PGSimpleDataSource();

    static {
        try(InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
            DATA_SOURCE.setUrl(PROPERTIES.getProperty("db.url"));
            DATA_SOURCE.setUser(PROPERTIES.getProperty("db.user"));
            DATA_SOURCE.setPassword(PROPERTIES.getProperty("db.password"));
        } catch (IOException e) {
            throw new RuntimeException("Can't load configuration file!");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}
