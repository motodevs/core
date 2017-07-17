package com.openvehicletracking.core.adapter;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;


/**
 * Created by oksuz on 14/05/2017.
 *
 */
public class GeoJsonResponseAdapter implements ResponseAdapter<JsonObject, List<JsonObject>> {

    /* sample result
    {
      "type": "FeatureCollection",
      "features": [
        {
          "type": "Feature",
          "properties": {},
          "geometry": {
            "type": "LineString",
            "coordinates": [
              [
                29.032084998725058,
                41.107373329066704
              ]
            ]
          }
        }
      ]
    }*/
    private JsonObject geoJson;

    private final static String FEATURE_TYPE = "Feature";
    private final static String LINE_STRING_TYPE = "LineString";
    private final static String FEATURE_COLLECTION_TYPE = "FeatureCollection";

    @Override
    public JsonObject result(List<JsonObject> toConvert) {
        initLineStringCollection();
        JsonArray geometries = geoJson.getJsonArray("features").getJsonObject(0).getJsonObject("geometry").getJsonArray("coordinates");

        toConvert.forEach(o -> {
            if (o.getDouble("longitude") != 0.0 && o.getDouble("latitude") != 0.0) {
                geometries.add(new JsonArray()
                        .add(o.getDouble("longitude"))
                        .add(o.getDouble("latitude")));
            }
        });

        return geoJson;
    }

    private void initLineStringCollection() {
        geoJson = new JsonObject();

        JsonArray features = new JsonArray();
        JsonObject feature = new JsonObject();
        JsonObject geometry = new JsonObject();
        JsonArray coordinates = new JsonArray();

        feature.put("type", FEATURE_TYPE);
        feature.put("properties", new JsonObject());
        feature.put("geometry", geometry);

        geometry.put("type", LINE_STRING_TYPE);
        geometry.put("coordinates", coordinates);

        features.add(feature);

        geoJson.put("type", FEATURE_COLLECTION_TYPE);
        geoJson.put("features", features);
    }
}
