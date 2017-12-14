package com.openvehicletracking.core.geojson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.openvehicletracking.core.JsonSerializeable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yo on 30/08/2017.
 */
public class GeoJsonResponse implements JsonSerializeable {

    private static final String TYPE = "FeatureCollection";
    private final List<Feature> features = new ArrayList<>();

    public void addFeature(final Feature feature) {
        features.add(feature);
    }

    @Override
    public String asJsonString() {
        JsonObject result = new JsonObject();
        JsonArray featuresNode = new JsonArray();

        for (Feature feature : this.features) {
            JsonObject f = new JsonObject();
            JsonObject featureGeometry = new JsonObject();
            JsonObject featureProperties = new JsonObject();
            JsonObject geometryProperties = new JsonObject();
            JsonArray geometryPoints = feature.getGeometry().getCoordinates();

            feature.getGeometry().getProperties().forEach(property -> geometryProperties.addProperty(property.getName(), property.getValue()));
            feature.getProperties().forEach(property -> featureProperties.addProperty(property.getName(), property.getValue()));

            featureGeometry.addProperty("type", feature.getGeometry().getType().asString());
            featureGeometry.add("coordinates", geometryPoints);
            featureGeometry.add("properties", geometryProperties);

            f.addProperty("type", feature.getType());
            f.add("geometry", featureGeometry);
            f.add("properties", featureProperties);
            featuresNode.add(f);
        }

        result.addProperty("type", TYPE);
        result.add("features", featuresNode);

        return result.toString();
    }

}
