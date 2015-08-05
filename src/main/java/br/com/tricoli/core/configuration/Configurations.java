package br.com.tricoli.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The class is a singleton to load the configuration properties.
 *
 * <p>This exception is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 */
public class Configurations {

    private static final Logger log = LoggerFactory.getLogger(Configurations.class);
    private static final String DEFAULT_CONFIGURATION_FILE = "/dr_config.properties";
    private static Properties config;

    /**
     * Initialize the {@code Configurations} from {@link #DEFAULT_CONFIGURATION_FILE}
     */
    public Configurations() {
        this(DEFAULT_CONFIGURATION_FILE);
    }

    /**
     * Initialize the {@code Configurations} from a specified file name
     */
    public Configurations(String fileName) {
        if(!fileName.startsWith("/")){
            throw new IllegalArgumentException("The configurationFile param needs to begin with slash");
        }

        log.info("Using {} as configuration file", fileName);
        InputStream propFile = getClass().getResourceAsStream(fileName);
        if (propFile == null) {
            throw new ConfigurationException("Configuration file not found in classpath: " + fileName);
        }

        log.debug("Load properties from {}", fileName);
        config = new Properties();
        try {
            config.load(propFile);
        } catch (IOException e) {
            throw new ConfigurationException("Cannot load configuration file", e);
        }
    }

    /**
     * Return a property for the key param
     *
     * @param key The key value of a property
     *
     * @return A {@link String} property
     */
    public String get(String key){
        return  config.getProperty(key);
    }

}
