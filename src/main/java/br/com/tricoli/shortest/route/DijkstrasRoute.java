package br.com.tricoli.shortest.route;

import br.com.tricoli.core.route.ShorterRoute;
import br.com.tricoli.core.route.RouteNotFoundException;
import br.com.tricoli.core.route.IndexedRoute;
import br.com.tricoli.core.route.Route;

import java.util.*;

/**
 * This class is the Dijkstra Algorithm implementation
 * to index the points and find the shorter route.
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 * @see <a href="http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html">Dijkstra Algorithm</a>
 *
 */
public final class DijkstrasRoute implements Route, IndexedRoute{

    /**
     * The points.
     */
    private Graph g;

    /**
     * It is the source point to index the {@link br.com.tricoli.shortest.route.Graph}
     */
    private final String source;

    /**
     * Constructs new {@link DijkstrasRoute}
     *
     * @param g is the base {@link br.com.tricoli.shortest.route.Graph} to be used
     */
    public DijkstrasRoute(Graph g) {
        this.g = g;
        this.source = null;
     }

    /**
     * This constructor is part of the {@link br.com.tricoli.core.route.IndexedRoute}.
     * It become possible to implemented the {@link br.com.tricoli.core.route.Route}
     * and {@link br.com.tricoli.core.route.IndexedRoute} in the same class.
     *
     * @param g is the indexed {@link br.com.tricoli.shortest.route.Graph}
     * @param source is the source point of {@link br.com.tricoli.shortest.route.Graph} param
     */
    private DijkstrasRoute(Graph g, String source) {
        this.g = g;
        this.source = source;
    }


    /**
     * It do the index of the {@link br.com.tricoli.shortest.route.Graph}
     * based in the Dijkstra Algorithm.
     *
     * @param source is the source point to index the {@link br.com.tricoli.shortest.route.Graph}
     *
     * @return A itself copy to used as DSL
     *
     * @see br.com.tricoli.core.route.Route
     */
    public IndexedRoute from(String source){
        g.getPoints().get(source).setDistance(0);

        Queue<Point> points = new LinkedList<>(g.getPoints().values());

        while (points.iterator().hasNext()){
            Collections.sort((List<Point>) points);
            Point smaller = points.poll();
            for (Point vertex : smaller.getNeighbours()) {
                long sum = smaller.getDistance() + smaller.getDistanceTo(vertex);
                if(sum < vertex.getDistance()){
                    vertex.setDistance(sum);
                    vertex.setPredecessor(smaller);
                }
            }

        }

        return new DijkstrasRoute(this.g,source);
    }

    /**
     * It check if there is a link between the {@link #source} and the target param.
     * It flow the points between the {@link #source} and the target param and
     * populate it in the {@link br.com.tricoli.core.route.ShorterRoute}
     *
     * @throws br.com.tricoli.core.route.RouteNotFoundException it there is no link link between the {@link #source} and the target param
     *
     * @param target
     *
     * @return A {@link br.com.tricoli.core.route.ShorterRoute} between the two points
     *
     * @see br.com.tricoli.core.route.IndexedRoute
     */
    public ShorterRoute to(String target) throws RouteNotFoundException {
        Point step = g.getPoints().get(target);
        if(step == null) {
            throw new RouteNotFoundException(String.format("There is no route from %s to %s", source, target));
        }

        ShorterRoute r = new ShorterRoute();
        r.addLastPoint(step.getName(), (int) step.getDistance());
        while ((step = step.getPredecessor()) != null) {
            r.addFirstPoint(step.getName(), (int) step.getDistance());
        }
        return r;
    }


}
