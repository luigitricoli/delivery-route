package br.com.tricoli.shortest.route;

import br.com.tricoli.core.entity.Distance;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represent a graph for the
 * {@link br.com.tricoli.shortest.route.DijkstrasRoute} algorithm.
 * The graph is the link of the points.
 *
 * <p>It is part of the {@link br.com.tricoli.shortest.route.DijkstrasRoute}.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class Graph {

    /**
     * The points.
     */
    private Map<String, Point> points;

    /**
     * Constructs new {@code Graph}. This constructor check all
     * {@link br.com.tricoli.core.entity.Distance}, then parse it to a
     * {@link br.com.tricoli.shortest.route.Point} linking their neighbours.
     *
     * @param distances The direct distances between points of one map.
     */
    public Graph(Collection<Distance> distances) {
        this.points = new HashMap<>();
        for(Distance d : distances){
            Point source = this.points.get(d.getSource());
            if(source == null){
                source = new Point(d.getSource());
                this.points.put(source.getName(), source);
            }

            Point target = this.points.get(d.getTarget());
            if(target == null){
                target = new Point(d.getTarget());
                this.points.put(target.getName(), target);
            }

            source.addNeighbour(target, d.getDistance());
            target.addNeighbour(source, d.getDistance());
        }
    }

    /**
     * @return The linked points
     */
    Map<String, Point> getPoints(){
        return points;
    }

}
