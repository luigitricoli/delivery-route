package br.com.tricoli.core.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represent the cost of a route.
 *
 * <p>It is prepared to be serialized as {@code XML} and {@code JSON}.
 * It is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Rota")
public class RouteCost {

    public static final int SCALE = 2;
    /**
     * All the points name that compose the route.
     * It is serialized as {@code XML} and {@code JSON} as <b>{@code rota}</b>.
     */
    @XmlElement(name="rota")
    private List<String> route;

    /**
     * The cost of the route.
     * It is serialized as {@code XML} and {@code JSON} as <b>{@code custo}</b>.
     */
    @XmlElement(name="custo")
    private BigDecimal cost;

    /**
     * Constructs a new empty {@code RouteCost}
     */
    public RouteCost() {
        this.route = new LinkedList<>();
        this.cost = BigDecimal.ZERO;
    }

    /**
     * Constructs a new {@code RouteCost}.
     *
     * <p>This calculate the cost of the route by
     * expression {@code (totalDistance / performance) * value}</p>
     *
     * @param route The points name that compose the route
     * @param totalDistance The distance between the first and the last point
     * @param performance The distance that can be take with one unit of value
     * @param value The value one unit
     */
    public RouteCost(List<String> route, Integer totalDistance, BigDecimal performance, BigDecimal value) throws CostException {
        if(lessThanZero(totalDistance)){
            throw new CostException("The totalDistance needs to be equals or greater than 0");
        }
        if(lessThanZero(performance)){
            throw new CostException("The performance needs to be equals or greater than 0");
        }
        if(lessThanZero(value)){
            throw new CostException("The value needs to be equals or greater than 0");
        }
        this.route = route;
        this.cost = calculateTheCost(totalDistance,performance,value);
    }

    private boolean lessThanZero(Integer number) {
        return number < 0;
    }

    private boolean lessThanZero(BigDecimal number) {
        return number.compareTo(BigDecimal.ZERO) < 0;
    }

    private BigDecimal calculateTheCost(Integer totalDistance, BigDecimal performance, BigDecimal value){
        return BigDecimal.valueOf(totalDistance).divide(performance, SCALE, BigDecimal.ROUND_HALF_UP).multiply(value).setScale(SCALE);
    }

    /**
     * @return A List<String> with the points name.
     */
    public List<String> getRoute() {
        return new LinkedList<>(route);
    }

    /**
     * @return The cost of this route.
     */
    public BigDecimal getCost() {
        return cost;
    }
}
