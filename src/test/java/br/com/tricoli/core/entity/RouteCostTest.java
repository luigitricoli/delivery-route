package br.com.tricoli.core.entity;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
public class RouteCostTest {

    @Test
    public void cost() throws CostException {
        RouteCost rc = new RouteCost(defaultRoute(), 6, BigDecimal.valueOf(5d), BigDecimal.valueOf(4.5d));

        assertEquals(BigDecimal.valueOf(5.40d).setScale(2), rc.getCost());
    }

    @Test(expected = CostException.class)
    public void invalidDistance() throws CostException {
        RouteCost rc = new RouteCost(defaultRoute(), -6, BigDecimal.valueOf(4.5d), BigDecimal.valueOf(4.5d));
        rc.getCost();
    }

    @Test(expected = CostException.class)
    public void invalidPerformance() throws CostException {
        RouteCost rc = new RouteCost(defaultRoute(), 6, BigDecimal.valueOf(-4.5d), BigDecimal.valueOf(4.5d));
        rc.getCost();
    }

    @Test(expected = CostException.class)
    public void invalidValue() throws CostException {
        RouteCost rc = new RouteCost(defaultRoute(), 6, BigDecimal.valueOf(4.5d), BigDecimal.valueOf(-4.5d));
        rc.getCost();
    }

    private List<String> defaultRoute() {
        return new LinkedList<String>(){{ add("S"); add("X"); add("U"); add("V"); }};
    }

}
