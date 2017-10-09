package com.openvehicletracking.core.geojson;

import java.util.List;

/**
 * Created by oksuz on 30/08/2017.
 *
 */
public class Feature {

    private final List<Property> properties;
    private final Geometry geometry;

    public Feature(List<Property> properties, Geometry geometry) {
        this.properties = properties;
        this.geometry = geometry;
    }

    public String getType() {
        return "Feature";
    }

    public List<Property> getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
