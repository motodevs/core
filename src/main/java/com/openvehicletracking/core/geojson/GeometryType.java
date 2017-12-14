package com.openvehicletracking.core.geojson;

public enum GeometryType {

    LINE_STRING("LineString"),
    POINT("Point");

    private final String val;

    GeometryType(String value) {
        this.val = value;
    }

    public String asString() {
        return val;
    }
}
