package br.com.tricoli.core.route;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represent the shorter route between to points.
 *
 * <p>It is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 * @see br.com.tricoli.core.route.IndexedRoute
 */
public class ShorterRoute {

    /**
     * All the path points with the relative distance of itself point and the source point.
     * <b>The source point needs to be the element that has distance equal to zero.</b>
     */
    private java.util.Map<String, Integer> route;

    /**
     * The distance of the utmost point to the source point.
     * The source point is the element that has distance zero.
     */
    private Integer utmost;


    /**
     * Constructs a new empty {@code ShorterRoute}
     */
    public ShorterRoute() {
       this(new LinkedHashMap<String, Integer>(), 0);
    }

    /**
     * Constructs a new populated {@code ShorterRoute}
     * 
     * @param route the points of the path
     * @param utmost the distance of the utmost point
     * 
     */
    public ShorterRoute(java.util.Map<String, Integer> route, Integer utmost) {
        this.route = new LinkedHashMap<>(route);
        this.utmost = utmost;
    }

    /**
     * @return {@link #utmost} - the {@code Integer} distance of the utmost point
     */
    public Integer getUtmost() {
        return utmost;
    }

    /**
     * Return the all the points name that compose the path
     *
     * @return A List<String> with the points name
     */
    public List<String> getFullPath(){
        return new LinkedList<>(route.keySet());
    }

    /**
     * Add a new last point. The distance of this point to source point will be
     * the new {@link #utmost} value.
     */
    public void addLastPoint(String name, Integer distance){
        route.put(name,distance);
        this.utmost = distance;
    }

    /**
     * Add a new first point.
     * <b>It is responsible of the user insert the element in the right order.</b>
     */
    public void addFirstPoint(String name, Integer distance){
        java.util.Map<String, Integer> tmp = new LinkedHashMap<>();
        tmp.put(name,distance);
        tmp.putAll(route);
        route = tmp;
    }
}
