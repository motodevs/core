package net.motodev.core.alarm;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by yo on 01/06/2017.
 */
public class Alarm {

    private String deviceId;
    private String description;
    private List<AlarmAction> actionList;
    private Date date;

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

    @Override
    public String toString() {
        return "Alarm{" +
                "date=" + date +
                ", actionList=" + actionList +
                ", description='" + description + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
