package com.openvehicletracking.core.message;

import com.google.gson.JsonObject;
import com.openvehicletracking.core.GpsStatus;

/**
 * Created by oksuz on 20/07/2017.
 *
 */
public interface LocationMessage extends Message {

    /**
     *
     * @return latitude
     */
    double getLatitude();

    /**
     *
     * @return longitude
     */
    double getLongitude();

    /**
     *
     * @return speed
     */
    double getSpeed();

    /**
     * direction as angle magnetic north | real north
     * @return direction
     */
    double getDirection();

    /**
     *
     * @return gpsStatus
     */
    GpsStatus getStatus();

    /**
     * @param jsonString jsonString
     * @param <T> message extending from LocationMessage
     * @return message
     */
    <T extends LocationMessage> T fromJson(String jsonString);

    /**
     *
     * @param json json
     * @param <T> message extending from LocationMessage
     * @return message
     */
    <T extends LocationMessage> T fromJson(JsonObject json);
}
