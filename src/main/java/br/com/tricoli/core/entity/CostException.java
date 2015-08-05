package br.com.tricoli.core.entity;

/**
 * The class {@code CostException} is specialized
 * {@link Exception} for throw exceptions
 * of cost validations.
 *
 * <p>This exception is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 */
public class CostException extends Exception {

    /**
     * Constructs a exception with the cause not initialized
     *
     * @see Exception
     */
    public CostException() {
        super();
    }

    /**
     * Constructs a exception with the specified detail message.
     *
     * @param message the detail message.
     *
     * @see Exception
     */
    public CostException(String message) {
        super(message);
    }

    /**
     * Constructs a exception with the specified detail message and
     * cause.
     *
     * @param message the detail message.
     * @param cause the cause normally with the previous {@link Exception}
     *
     * @see Exception
     */
    public CostException(String message, Throwable cause) {
        super(message, cause);
    }
}