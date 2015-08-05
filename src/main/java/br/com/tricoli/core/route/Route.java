package br.com.tricoli.core.route;

/**
 * By implementing this interface an class demonstrate
 * the capacity of index its route points.
 *
 * <p>This interface works together with {@link br.com.tricoli.core.route.IndexedRoute}
 * to create a DSL. It is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 * @see br.com.tricoli.core.route.IndexedRoute
 */
public interface Route {

    /**
     * Return a {@link br.com.tricoli.core.route.IndexedRoute}
     * indexed relative to the give source param.
     *
     * @param source - the root element
     */
    public IndexedRoute from(String source);
}
