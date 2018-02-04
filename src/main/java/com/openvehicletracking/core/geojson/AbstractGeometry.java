package com.openvehicletracking.core.geojson;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractGeometry {

    private final List<GeoJsonProperty> properties = new ArrayList<>();
    private final List<Point> coordinates = new ArrayList<>();

    public void addPoint(final Point p) {
        coordinates.add(0, p);
    }

    public List<Point> getCoordinateList() {
        return coordinates;
    }

    public void addProperty(final GeoJsonProperty p) {
        properties.add(p);
    }

    public List<GeoJsonProperty> getPropertyList() {
        return properties;
    }

}
