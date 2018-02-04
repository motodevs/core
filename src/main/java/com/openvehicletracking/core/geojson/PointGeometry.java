package com.openvehicletracking.core.geojson;

import com.google.gson.JsonArray;

import java.util.List;

/**
 * Created by oksuz on 30/08/2017.
 *
 */
public class PointGeometry extends AbstractGeometry implements Geometry {

    @Override
    public GeometryType getType() {
        return GeometryType.POINT;
    }

    @Override
    public JsonArray getCoordinates() {
        List<Point> coords = getCoordinateList();
        JsonArray array = new JsonArray();

        if (coords.size() > 0) {
            array.add(coords.get(0).getLongitude());
            array.add(coords.get(0).getLatitude());
        }

        return array;
    }

    @Override
    public List<GeoJsonProperty> getProperties() {
        return getPropertyList();
    }
}
