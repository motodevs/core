package com.openvehicletracking.core.message;

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
}
