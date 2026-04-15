package org.example.dbhelper;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Factory class responsible for creating and managing database connections.
 * <p>
 * Uses {@link PGSimpleDataSource} as underlying implementation of {@link DataSource}.
 * The factory must be initialized before use via {@link #initFactory(Properties)}.
 * </p>
 */
public class ConnectionFactory {

    /**
     * Internal DataSource instance used to create connections.
     */
    private static DataSource dataSource;

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * This class is a utility/factory class and should not be instantiated.
     * All methods are accessed in a static way.
     * </p>
     */
    private ConnectionFactory() {
    }

    /**
     * Initializes the connection factory with database configuration properties.
     *
     * <p>
     * Expected properties:
     * <ul>
     *     <li>{@code url} - JDBC URL of the database</li>
     *     <li>{@code user} - database username</li>
     *     <li>{@code password} - database password</li>
     * </ul>
     * </p>
     *
     * @param properties configuration properties for database connection
     */
    public static void initFactory(Properties properties){
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(properties.getProperty("db.url"));
        ds.setUser(properties.getProperty("db.user"));
        ds.setPassword(properties.getProperty("db.password"));
        dataSource = ds;
    }

    /**
     * Provides a new database connection from the initialized DataSource.
     *
     * @return active database connection
     * @throws SQLException if a database access error occurs
     * @throws IllegalStateException if the factory has not been initialized
     */
    public static Connection getConnection() throws SQLException {
        if(dataSource == null){
            throw new IllegalStateException("ConnectionFactory has not been initialized");
        }
        return dataSource.getConnection();
    }
}
