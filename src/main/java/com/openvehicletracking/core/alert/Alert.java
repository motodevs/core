package com.openvehicletracking.core.alert;


import com.google.gson.JsonObject;
import com.openvehicletracking.core.GsonFactory;
import com.openvehicletracking.core.JsonSerializeable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by oksuz on 01/06/2017.
 * Alert model
 */
public class Alert implements Serializable, JsonSerializeable {

    private String deviceId;
    private String description;
    private List<AlertAction> actionList;
    private long datetime;
    private JsonObject extraData;

    public Alert(String deviceId, String description, List<AlertAction> actionList, long datetime) {
        Objects.requireNonNull(deviceId);
        Objects.requireNonNull(description);
        Objects.requireNonNull(actionList);

        this.deviceId = deviceId;
        this.description = description;
        this.actionList = actionList;
        this.datetime = datetime;
    }

    public Alert(String deviceId, String description, List<AlertAction> actionList, long datetime, JsonObject extraData) {
        this(deviceId, description, actionList, datetime);
        this.extraData = extraData;
    }

    @Override
    public String asJsonString() {
        return GsonFactory.getGson().toJson(this);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDescription() {
        return description;
    }

    public List<AlertAction> getActions() {
        return actionList;
    }

    public long getDatetime() {
        return datetime;
    }

    public JsonObject getExtraData() {
        return extraData;
    }

    public void setExtraData(JsonObject extraData) {
        this.extraData = extraData;
    }

    @Override
    public String toString() {
        return GsonFactory.getGson().toJson(this);
    }
}
