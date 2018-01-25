package com.openvehicletracking.core;

import com.openvehicletracking.core.protocol.LocationMessage;

import java.util.Date;
import java.util.HashMap;

public abstract class AbstractLocationMessageBuilder<T extends LocationMessage> implements MessageBuilder<T> {

    private Position position;
    private GpsStatus gpsStatus;
    private double accuracy;
    private Date date;
    private HashMap<String, Object> attributes = new HashMap<>();
    private Object raw;
    private String protocol;

    public AbstractLocationMessageBuilder gpsStatus(GpsStatus gpsStatus) {
        this.gpsStatus = gpsStatus;
        return this;
    }

    public AbstractLocationMessageBuilder position(Position position) {
        this.position = position;
        return this;
    }

    public AbstractLocationMessageBuilder accuracy(double accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public AbstractLocationMessageBuilder date(Date date) {
        this.date = date;
        return this;
    }

    public AbstractLocationMessageBuilder attribute(String key, Object value) {
        attributes.put(key, value);
        return this;
    }

    @Override
    public AbstractLocationMessageBuilder raw(Object raw) {
        this.raw = raw;
        return this;
    }

    @Override
    public AbstractLocationMessageBuilder protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public GpsStatus getGpsStatus() {
        return gpsStatus;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public Date getDate() {
        return date;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public Object getRaw() {
        return raw;
    }

    public String getProtocol() {
        return protocol;
    }
}
