package com.openvehicletracking.core.geojson;

import com.google.gson.JsonArray;

import java.util.List;

public interface Geometry {

    GeometryType getType();

    JsonArray getCoordinates();

    List<GeoJsonProperty> getProperties();

}
