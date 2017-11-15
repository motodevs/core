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

    private final String type = "FeatureCollection";
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
            JsonArray geometryPoints = new JsonArray();


            feature.getGeometry().getCoordinates().forEach(point -> {
                JsonArray p = new JsonArray();
                p.add(point.getLongitude());
                p.add(point.getLatitude());
                geometryPoints.add(p);
            });

            feature.getGeometry().getProperties().forEach(property -> geometryProperties.addProperty(property.getName(), property.getValue()));


            featureGeometry.addProperty("type", feature.getGeometry().getType());
            featureGeometry.add("coordinates", geometryPoints);
            featureGeometry.add("properties", geometryProperties);

            f.addProperty("type", feature.getType());
            f.add("geometry", featureGeometry);
            f.add("properties", featureProperties);
            featuresNode.add(f);
        }

        result.addProperty("type", type);
        result.add("features", featuresNode);

        return result.toString();
    }

}
