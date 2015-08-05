package br.com.tricoli.core.configuration;

/**
 * The class {@code ConfigurationException} is specialized
 * {@link java.lang.Exception} for throw exceptions
 * of {@link br.com.tricoli.core.configuration.Configurations}.
 *
 * <p>This exception is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 */
public class ConfigurationException extends RuntimeException {

    /**
     * Constructs a exception with the cause not initialized
     *
     * @see java.lang.RuntimeException
     */
    public ConfigurationException() {
        super();
    }

    /**
     * Constructs a exception with the specified detail message.
     *
     * @param message the detail message.
     *
     * @see java.lang.RuntimeException
     */
    public ConfigurationException(String message) {
        super(message);
    }

    /**
     * Constructs a exception with the specified detail message and
     * cause.
     *
     * @param message the detail message.
     * @param cause the cause normally with the previous {@link java.lang.Exception}
     *
     * @see java.lang.RuntimeException
     */
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}