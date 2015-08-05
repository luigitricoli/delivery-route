package br.com.tricoli.shortest.route;

import br.com.tricoli.core.entity.Distance;
import br.com.tricoli.core.route.RouteNotFoundException;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class GraphTest {

    @Test
    public void oneNeighbour(){
        List<Distance> distances = new LinkedList<>();
        distances.add(new Distance("S", "U", 10));
        distances.add(new Distance("S", "X", 5));
        distances.add(new Distance("U", "V", 1));
        distances.add(new Distance("U", "X", 3));
        distances.add(new Distance("X", "V", 9));
        distances.add(new Distance("V", "Y", 6));
        distances.add(new Distance("Y", "X", 5));
        distances.add(new Distance("Z", "M", 5));

        Graph g = new Graph(distances);
        Collection<Point> zNeighbours = g.getPoints().get("Z").getNeighbours();;

        assertEquals(1, zNeighbours.size());
        assertNotNull(zNeighbours.contains(new Point("M")));
    }

    @Test
    public void manyNeighbour(){
        List<Distance> distances = new LinkedList<>();
        distances.add(new Distance("S", "U", 10));
        distances.add(new Distance("S", "X", 5));
        distances.add(new Distance("U", "V", 1));
        distances.add(new Distance("U", "X", 3));
        distances.add(new Distance("X", "V", 9));
        distances.add(new Distance("V", "Y", 6));
        distances.add(new Distance("Y", "X", 5));

        Graph g = new Graph(distances);
        Collection<Point> yNeighbours = g.getPoints().get("Y").getNeighbours();

        List<Point> expected = new LinkedList<>();
        expected.add(new Point("V"));
        expected.add(new Point("X"));

        assertEquals(expected.size(), yNeighbours.size());
        for (Point point : expected) {
            assertNotNull(yNeighbours.contains(point));
        }
    }

    @Test
    public void noPoint(){
        List<Distance> distances = new LinkedList<>();
        distances.add(new Distance("S", "U", 10));
        distances.add(new Distance("S", "X", 5));
        distances.add(new Distance("U", "V", 1));
        distances.add(new Distance("U", "X", 3));
        distances.add(new Distance("X", "V", 9));
        distances.add(new Distance("V", "Y", 6));
        distances.add(new Distance("Y", "X", 5));
        distances.add(new Distance("Z", "M", 5));

        Graph g = new Graph(distances);
        assertNull(g.getPoints().get("J"));
    }

}
