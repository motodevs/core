package com.openvehicletracking.core;


import com.openvehicletracking.core.protocol.Message;

/**
 * @author oksuz
 *
 * Generic tracking device.
 */
public interface Device {

    String getName();

    String getId();

    DeviceState getState();

    Device createStateFromMessage(Message message);

    void addConnection(ConnectionHolder<?> connectionHolder);

    ConnectionHolder<?> getConnection();
}
