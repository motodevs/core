package com.openvehicletracking.core;


/**
 * @author oksuz
 *
 * Generic tracking device.
 */
public interface Device {

    String getName();

    String getId();

    DeviceState getState();

    void addConnection(ConnectionHolder<?> connectionHolder);

    ConnectionHolder<?> getConnection();
}
