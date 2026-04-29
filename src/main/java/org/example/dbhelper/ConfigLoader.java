package org.example.dbhelper;

import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class responsible for loading application configuration
 * from the {@code application.properties} file located in the classpath.
 * <p>
 * This class is used to read database and other runtime configuration
 * properties required for application initialization.
 * </p>
 */
public class ConfigLoader {

    /**
     * Name of the configuration file located in the resources directory.
     */
    private static final String CONFIG_FILE = "application.properties";

    /**
     * Loads configuration properties from the {@code application.properties} file.
     *
     * @return loaded properties containing application configuration
     * @throws RuntimeException if the configuration file is not found
     *                          or cannot be loaded
     */
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
