package org.example.dbhelper;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static DataSource dataSource;

    private ConnectionFactory() {
    }

    public static void initFactory(Properties properties){
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(properties.getProperty("db.url"));
        ds.setUser(properties.getProperty("db.user"));
        ds.setPassword(properties.getProperty("db.password"));
        dataSource = ds;
    }

    public static Connection getConnection() throws SQLException {
        if(dataSource == null){
            throw new IllegalStateException("ConnectionFactory has not been initialized");
        }
        return dataSource.getConnection();
    }
}
