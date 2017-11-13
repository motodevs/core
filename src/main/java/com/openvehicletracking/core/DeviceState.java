package com.openvehicletracking.core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by oksuz on 20/08/2017.
 *
 */
public class DeviceState implements Serializable {

    private String deviceId;
    private double distance;
    private long createdAt;
    private long updatedAt;
    private long deviceDate;
    private double latitude;
    private double longitude;
    private double direction;
    private double speed;
    private boolean ignitionKeyOff;
    private boolean invalidDeviceDate;
    private DeviceStatus deviceStatus;
    private GpsStatus gpsStatus;
    private static Gson gson = new Gson();

    public static DeviceState fromJson(JsonObject object) {
        return gson.fromJson(object, DeviceState.class);
    }

    public JsonObject toJson() {
        JsonParser parser = new JsonParser();
        return parser.parse(gson.toJson(this)).getAsJsonObject();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public GpsStatus getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(GpsStatus gpsStatus) {
        this.gpsStatus = gpsStatus;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getDeviceDate() {
        return deviceDate;
    }

    public void setDeviceDate(long deviceDate) {
        this.deviceDate = deviceDate;
    }

    public boolean isIgnitionKeyOff() {
        return ignitionKeyOff;
    }

    public void setIgnitionKeyOff(boolean ignitionKeyOff) {
        this.ignitionKeyOff = ignitionKeyOff;
    }

    public boolean isInvalidDeviceDate() {
        return invalidDeviceDate;
    }

    public void setInvalidDeviceDate(boolean invalidDeviceDate) {
        this.invalidDeviceDate = invalidDeviceDate;
    }

    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "DeviceState{" +
                "deviceId='" + deviceId + '\'' +
                ", distance=" + distance +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", deviceStatus=" + deviceStatus +
                ", gpsStatus=" + gpsStatus +
                ", createdAt=" + new Date(createdAt) +
                ", deviceDate=" + new Date(deviceDate) +
                '}';
    }
}
