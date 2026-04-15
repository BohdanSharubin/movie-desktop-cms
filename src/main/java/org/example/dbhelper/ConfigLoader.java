package org.example.dbhelper;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final String CONFIG_FILE = "application.properties";

    public Properties loadConfig(){
        Properties properties = new Properties();
        try (InputStream inputStream =
                     getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {

            if (inputStream == null) {
                throw new RuntimeException(CONFIG_FILE + " not found in resources");
            }

            properties.load(inputStream);
            return properties;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }
}
