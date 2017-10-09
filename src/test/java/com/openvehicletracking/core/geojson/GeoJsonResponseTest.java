package com.openvehicletracking.core.geojson;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by oksuz on 30/08/2017.
 *
 */
public class GeoJsonResponseTest {


    @Test
    public void testGeoJsonResponse() {
        String expectedJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"LineString\",\"coordinates\":[[987.0,678.0],[456.0,123.0]],\"properties\":{}},\"properties\":{}}]}";
        GeoJsonResponse response = new GeoJsonResponse();

        Geometry geometry = new Geometry("LineString");
        geometry.addPoint(new Point(123,456));
        geometry.addPoint(new Point(678,987));

        Feature feature = new Feature(new ArrayList<>(), geometry);
        response.addFeature(feature);


        Assert.assertEquals(expectedJson, response.toJson().toString());

    }


}