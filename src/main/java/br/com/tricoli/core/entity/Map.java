package br.com.tricoli.core.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represent a map.
 *
 * <p>It is prepared to be serialized as {@code XML} and {@code JSON}.
 * It is part of the core domain.</p>
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Map {

    /**
     * The map name.
     * It is serialized as {@code XML} and {@code JSON} as <b>{@code mapa}</b>.
     */
    @XmlElement(name="mapa")
    private String name;

    /**
     * The distance between the possible direct links of the map.
     * It is serialized as {@code XML} and {@code JSON} as <b>{@code distancias}</b>.
     */
    @XmlElement(name="distancias")
    private List<Distance> distances;

    /**
     * Constructs a new empty {@code Map}
     */
    public Map() {
        this(null, new ArrayList<Distance>());
    }

    /**
     * Constructs a new {@code Map} without {@link #distances}.
     *
     * @param name The map name
     */
    public Map (String name) {
        this(name, new ArrayList<Distance>());
    }

    /**
     * Constructs a new {@code Map}.
     *
     * @param name The map name
     * @param distances The distance between the possible direct links of the map
     */
    public Map(String name, List<Distance> distances) {
        this.name = name;
        this.distances = distances;
    }

    /**
     * @return The map name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The {@link #distances}.
     */
    public List<Distance> getDistances() {
        return Collections.unmodifiableList(distances);
    }

    /**
     * Add a new distance to the map
     */
    public void addDistance(Distance d){
        this.distances.add(d);
    }

    @Override
    public String toString() {
        return "Map{" +
                "name='" + name + '\'' +
                ", distances=" + distances +
                '}';
    }
}
