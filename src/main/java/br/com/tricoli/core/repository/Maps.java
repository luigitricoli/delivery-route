package br.com.tricoli.core.repository;

import br.com.tricoli.core.entity.Map;

import java.sql.SQLException;

/**
 * By implementing this interface an class demonstrate
 * the capacity of be {@link br.com.tricoli.core.entity.Map} repository.
 *
 * <p>It is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public interface Maps {

    /**
     * Return a {@link br.com.tricoli.core.entity.Map}
     * selected from the {@code Map} name.
     *
     * @param name - the {@code Map} name
     *
     * @return The selected {@code Map}
     */
    public Map get(String name);

    /**
     * Add a new {@link br.com.tricoli.core.entity.Map}
     *
     * @param map - the new {@code Map}
     */
    public void add(Map map) throws SQLException;
}
