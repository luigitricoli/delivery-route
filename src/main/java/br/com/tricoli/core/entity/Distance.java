package br.com.tricoli.core.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represent a distance between tow points.
 *
 * <p>It is prepared to be serialized as {@code XML} and {@code JSON}.
 * It is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Distancia")
public class Distance {

    /**
     * The source point.
     * It is serialized as {@code XML} and {@code JSON} as <b>{@code origem}</b>.
     */
    @XmlElement(name="origem")
    private String source;

    /**
     * The target point.
     * It is serialized as {@code XML} and {@code JSON} as <b>{@code destino}</b>.
     */
    @XmlElement(name="destino")
    private String target;

    /**
     * The distance between the points.
     * It is serialized as {@code XML} and {@code JSON} as <b>{@code valor}</b>.
     */
    @XmlElement(name="valor")
    private Integer length;

    /**
     * Constructs a new empty {@code Distance}
     */
    public Distance() {
    }

    /**
     * Constructs a new {@code Map}.
     *
     * @param source The source point name
     * @param target The target point name
     * @param length The distance between the points
     */
    public Distance(String source, String target, Integer length) {
        this.source = source;
        this.target = target;
        this.length = length;
    }

    /**
     * @return the {@link #source} point.
     */
    public String getSource() {
        return source;
    }

    /**
     * @return the {@link #target} point.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @return the {@link #length} between the points.
     */
    public Integer getDistance() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Distance edge = (Distance) o;

        if (source != null ? !source.equals(edge.source) : edge.source != null) return false;
        if (target != null ? !target.equals(edge.target) : edge.target != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", length=" + length +
                '}';
    }
}
