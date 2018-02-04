package com.openvehicletracking.core.message.impl;

import com.google.gson.JsonArray;
import com.openvehicletracking.core.alert.Alert;
import com.openvehicletracking.core.message.CommandMessage;

import java.util.Optional;

/**
 * Created by oksuz on 05/09/2017.
 *
 */
public abstract class AbstractCommandMessage implements CommandMessage {

    protected String command;
    protected String requestId;
    protected String device;
    protected String deviceId;
    protected String type;
    protected long datetime;
    protected JsonArray extraParameters;
    protected boolean isRead;

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

    @Override
    public boolean isReplyRequired() {
        return false;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public void setExtraParameters(JsonArray extraParameters) {
        this.extraParameters = extraParameters;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
