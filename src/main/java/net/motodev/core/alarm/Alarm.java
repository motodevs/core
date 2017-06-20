package net.motodev.core.alarm;

import java.util.Date;
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
    private Date date;
    private HashMap<Object, Object> extraData;

    public Alarm(String deviceId, String description, List<AlarmAction> actionList, Date date) {
        Objects.requireNonNull(deviceId);
        Objects.requireNonNull(description);
        Objects.requireNonNull(actionList);
        Objects.requireNonNull(date);

        this.deviceId = deviceId;
        this.description = description;
        this.actionList = actionList;
        this.date = date;
    }

    public Alarm(String deviceId, String description, List<AlarmAction> actionList, Date date, HashMap<Object, Object> extraData) {
        Objects.requireNonNull(deviceId);
        Objects.requireNonNull(description);
        Objects.requireNonNull(actionList);
        Objects.requireNonNull(date);

        this.deviceId = deviceId;
        this.description = description;
        this.actionList = actionList;
        this.date = date;
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

    public Date date() {
        return date;
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
                ", date=" + date +
                '}';
    }
}
