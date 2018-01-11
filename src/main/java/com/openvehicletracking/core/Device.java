package com.openvehicletracking.core;


/**
 * @author oksuz
 *
 * Generic tracking device.
 */
public interface Device {

    /**
     *
     * @return name
     */
    String getName();

    /**
     *
     * @return id
     */
    String getId();


    /**
     *
     * @return state of device
     */
    DeviceState getState();

}
