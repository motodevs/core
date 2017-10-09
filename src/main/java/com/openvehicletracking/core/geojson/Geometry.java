package com.openvehicletracking.core.geojson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oksuz on 30/08/2017.
 *
 */
public class Geometry {

    private final String type;
    private final List<Property> properties = new ArrayList<>();
    private final List<Point> coordinates = new ArrayList<>();

    public Geometry(String type) {
        this.type = type;
    }

    public void addPoint(final Point p) {
        coordinates.add(0, p);
    }

    public void addProperty(final Property p) {
        properties.add(p);
    }

    public String getType() {
        return type;
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    public List<Property> getProperties() {
        return properties;
    }
}
