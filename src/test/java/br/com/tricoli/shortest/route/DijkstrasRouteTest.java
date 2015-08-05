package br.com.tricoli.shortest.route;

import br.com.tricoli.core.entity.Distance;
import br.com.tricoli.core.route.RouteNotFoundException;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

/**
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class DijkstrasRouteTest {

    @Test
    public void oneEdgesOneRoute() throws RouteNotFoundException {
        Set<Distance> distances = new HashSet<>();
        distances.add(new Distance("A", "B", 20));

        DijkstrasRoute route = new DijkstrasRoute(new Graph(distances));

        List<String> path = new LinkedList<String>(){{ add("A"); add("B"); }};

        assertEquals(path, route.from("A").to("B").getFullPath());
        assertEquals(Integer.valueOf(20), route.from("A").to("B").getUtmost());
    }

    @Test
    public void twoEdgesOneRoute() throws RouteNotFoundException {
        Set<Distance> distances = new HashSet<>();
        distances.add(new Distance("A", "B", 20));
        distances.add(new Distance("B", "C", 20));

        DijkstrasRoute route = new DijkstrasRoute(new Graph(distances));

        List<String> path = new LinkedList<String>(){{ add("A"); add("B"); add("C"); }};

        assertEquals(path, route.from("A").to("C").getFullPath());
        assertEquals(Integer.valueOf(20), route.from("A").to("B").getUtmost());
    }

    @Test
    public void manyEdgesManyRoute() throws RouteNotFoundException {
        List<Distance> distances = new LinkedList<>();
        distances.add(new Distance("S", "U", 10));
        distances.add(new Distance("S", "X", 5));
        distances.add(new Distance("U", "V", 1));
        distances.add(new Distance("U", "X", 3));
        distances.add(new Distance("X", "V", 9));
        distances.add(new Distance("V", "Y", 6));
        distances.add(new Distance("Y", "X", 5));

        DijkstrasRoute route = new DijkstrasRoute(new Graph(distances));

        List<String> path = new LinkedList<String>(){{ add("S"); add("X"); add("U"); add("V"); }};

        assertEquals(path, route.from("S").to("V").getFullPath());
        assertEquals(Integer.valueOf(9), route.from("S").to("V").getUtmost());
    }

    @Test(expected = RouteNotFoundException.class)
    public void twoEdgesNoRoute() throws RouteNotFoundException {
        Set<Distance> distances = new HashSet<>();
        distances.add(new Distance("A", "B", 20));
        distances.add(new Distance("B", "C", 20));

        DijkstrasRoute route = new DijkstrasRoute(new Graph(distances));

        route.from("A").to("D");
    }

}
