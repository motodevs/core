package com.openvehicletracking.core.geojson;

/**
 * Created by yo on 30/08/2017.
 */
public class Property {

    private final String name;
    private final String value;

    public Property(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
