package com.openvehicletracking.core.geojson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by oksuz on 30/08/2017.
 *
 */
public class Feature {

    private final List<GeoJsonProperty> properties;
    private final Geometry geometry;
    private static final String FEATURE = "Feature";

    public Feature(List<GeoJsonProperty> properties, Geometry geometry) {
        if (properties == null) {
            properties = new ArrayList<>();
        }
        Objects.requireNonNull(geometry, "geometry cannot be null");
        this.properties = properties;
        this.geometry = geometry;
    }

    public String getType() {
        return FEATURE;
    }

    public List<GeoJsonProperty> getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
