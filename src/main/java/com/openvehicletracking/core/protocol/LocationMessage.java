package com.openvehicletracking.core.protocol;

import com.google.gson.JsonObject;
import com.openvehicletracking.core.GpsStatus;
import com.openvehicletracking.core.json.GsonFactory;

/**
 * Created by oksuz on 20/07/2017.
 *
 */
public interface LocationMessage extends Message {

    double getLatitude();

    double getLongitude();

    double getSpeed();

    double getDirection();

    double getAccuracy();

    GpsStatus getStatus();

    static <T extends LocationMessage> T fromJson(String jsonString, Class<T> type) {
        return GsonFactory.getGson().fromJson(jsonString, type);
    }

    static <T extends LocationMessage> T fromJson(JsonObject json, Class<T> type) {
        return GsonFactory.getGson().fromJson(json, type);
    }
}
