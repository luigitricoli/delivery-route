package br.com.tricoli.core.route;

/**
 * By implementing this interface an class demonstrate
 * the capacity of return the shortest route from a index route.
 *
 * <p>This interface works together with {@link br.com.tricoli.core.route.Route}
 * to create a DSL. It is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 * @see br.com.tricoli.core.route.Route
 * @see ShorterRoute
 */
public interface IndexedRoute {

    /**
     * Return a {@link ShorterRoute}
     * from source, done by calling {@link br.com.tricoli.core.route.Route#from(String)},
     * to the target param.
     *
     * @param target - the final element
     *
     * @exception br.com.tricoli.core.route.RouteNotFoundException if there is no link between the
     * source of {@link br.com.tricoli.core.route.Route#from(String)} and the target
     *
     * @see ShorterRoute
     */
    public ShorterRoute to(String target) throws RouteNotFoundException;

}
