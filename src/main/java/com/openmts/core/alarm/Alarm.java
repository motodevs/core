package com.openmts.core.alarm;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by oksuz on 01/06/2017.
 *
 */
public class Alarm {

    private String deviceId;
    private String description;
    private List<AlarmAction> actionList;
    private long datetime;
    private HashMap<Object, Object> extraData;

    public Alarm(String deviceId, String description, List<AlarmAction> actionList, long datetime) {
        Objects.requireNonNull(deviceId);
        Objects.requireNonNull(description);
        Objects.requireNonNull(actionList);
        Objects.requireNonNull(datetime);

        this.deviceId = deviceId;
        this.description = description;
        this.actionList = actionList;
        this.datetime = datetime;
    }

    public Alarm(String deviceId, String description, List<AlarmAction> actionList, long datetime, HashMap<Object, Object> extraData) {
        this(deviceId, description, actionList, datetime);
        this.extraData = extraData;
    }

    public String deviceId() {
        return deviceId;
    }

    public String description() {
        return description;
    }

    public List<AlarmAction> actionList() {
        return actionList;
    }

    public long datetime() {
        return datetime;
    }

    public HashMap<Object, Object> extraData() {
        return extraData;
    }

    public void setExtraData(HashMap<Object, Object> extraData) {
        this.extraData = extraData;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "deviceId='" + deviceId + '\'' +
                ", description='" + description + '\'' +
                ", actionList=" + actionList +
                ", datetime=" + datetime +
                '}';
    }
}
