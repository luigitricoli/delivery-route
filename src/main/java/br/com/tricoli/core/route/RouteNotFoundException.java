package br.com.tricoli.core.route;

/**
 * The class {@code RouteNotFoundException} is specialized
 * {@link java.lang.Exception} for route algorithm.
 *
 * <p>This exception is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 */
public class RouteNotFoundException extends Exception {

    /**
     * Constructs a exception with the cause not initialized
     *
     * @see java.lang.Exception
     */
    public RouteNotFoundException() {
        super();
    }

    /**
     * Constructs a exception with the specified detail message.
     *
     * @param   message   the detail message.
     *
     * @see java.lang.Exception
     */
    public RouteNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a exception with the specified detail message and
     * cause.
     *
     * @param  message the detail message.
     * @param  cause the cause normally with the previous {@link java.lang.Exception}
     *
     * @see java.lang.Exception
     */
    public RouteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
