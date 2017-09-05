package com.openvehicletracking.core.message.impl;

import com.google.gson.JsonArray;
import com.openvehicletracking.core.message.CommandMessage;

import java.util.Optional;

/**
 * Created by oksuz on 05/09/2017.
 *
 */
public class CommandMessageImpl implements CommandMessage {

    private String command;
    private String requestId;
    private String device;
    private String deviceId;
    private String type;
    private long datetime;
    private JsonArray extraParameters;
    private boolean isRead;

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public Optional<String> getRequestId() {
        return Optional.of(requestId);
    }

    @Override
    public String getDevice() {
        return device;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public Optional<String> getType() {
        return Optional.of(type);
    }

    @Override
    public boolean isCommand() {
        return true;
    }

    @Override
    public long getDatetime() {
        return datetime;
    }

    @Override
    public Optional<JsonArray> getExtraParameters() {
        return Optional.of(extraParameters);
    }

    @Override
    public boolean isRead() {
        return isRead;
    }

}
