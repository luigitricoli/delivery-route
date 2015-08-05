package br.com.tricoli.shortest.route;

import java.util.*;

/**
 * This class represent a point in the {@link br.com.tricoli.shortest.route.Graph}.
 *
 * <p>It is part of the {@link br.com.tricoli.shortest.route.DijkstrasRoute}.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class Point implements Comparable<Point>{

    /**
     * The poing name.
     */
    private String name;

    /**
     * The distance of it to the reference point as source.
     */
    private long distance;

    /**
     * The point tha precede this point.
     */
    private Point predecessor;

    /**
     * The neighbours of this point
     */
    private Map<Point, Integer> neighbours;

    /**
     * Constructs new empty {@code Point}
     *
     * @param name The name of the new Point
     */
    public Point(String name) {
        this.name = name;
        this.neighbours = new HashMap<>();
        this.distance = System.nanoTime();
    }

    /**
     * @return The map name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The {@link #distance}.
     */
    public long getDistance() {
        return distance;
    }

    /**
     * Set the {@link #distance}.
     *
     * @param distance The new distance
     */
    public void setDistance(long distance) {
        this.distance = distance;
    }

    /**
     * @return The {@link #predecessor}.
     */
    public Point getPredecessor() {
        return predecessor;
    }

    /**
     * Set the {@link #predecessor}.
     *
     * @param predecessor The new distance
     */
    public void setPredecessor(Point predecessor) {
        this.predecessor = predecessor;
    }

    /**
     * @return The neighbours of this point
     */
    public Collection<Point> getNeighbours() {
        return Collections.unmodifiableSet(neighbours.keySet());
    }

    /**
     * Add a new neighbour
     */
    public void addNeighbour(Point v, Integer distance){
        neighbours.put(v,distance);
    }

    /**
     * @return The distance of this point to one of its neighbour
     */
    public Integer getDistanceTo(Point v){
        return neighbours.get(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point vertex = (Point) o;

        if (!name.equals(vertex.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Point other) {
        return Long.compare(distance, other.distance);
    }

    @Override
    public String toString() {
        return "Point{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                ", predecessor=" + predecessor +
                '}';
    }
}
