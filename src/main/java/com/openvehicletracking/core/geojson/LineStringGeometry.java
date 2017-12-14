package com.openvehicletracking.core.geojson;

import com.google.gson.JsonArray;

import java.util.List;

public class LineStringGeometry extends AbstractGeometry implements Geometry {

    @Override
    public GeometryType getType() {
        return GeometryType.LINE_STRING;
    }

    @Override
    public JsonArray getCoordinates() {
        JsonArray geometryPoints = new JsonArray();
        getCoordinateList().forEach(point -> {
            JsonArray p = new JsonArray();
            p.add(point.getLongitude());
            p.add(point.getLatitude());
            geometryPoints.add(p);
        });

        return geometryPoints;
    }

    @Override
    public List<GeoJsonProperty> getProperties() {
        return getPropertyList();
    }
}
