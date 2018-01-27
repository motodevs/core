package com.openvehicletracking.core.alert;

import com.openvehicletracking.core.json.GsonFactory;

import java.util.*;

/**
 * Created by oksuz on 01/06/2017.
 *
 */
public class AlertImp implements Alert {

    private final String description;
    private final int id;
    private final List<AlertAction> actions;
    private final HashMap<String, Object> extras;
    private final long date;

    private AlertImp(AlertBuilder alertBuilder) {
        description = alertBuilder.description;
        id = alertBuilder.id;
        actions = alertBuilder.actions;
        extras = alertBuilder.extra;
        date = alertBuilder.date.getTime();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getAlertId() {
        return id;
    }

    @Override
    public List<AlertAction> getActions() {
        return actions;
    }

    @Override
    public Date getAlertDate() {
        return new Date(date);
    }

    @Override
    public HashMap<String, Object> getExtraData() {
        return extras;
    }

    @Override
    public String asJson() {
        return GsonFactory.getGson().toJson(this);
    }

    public static class AlertBuilder {

        String description;
        int id;
        List<AlertAction> actions = new ArrayList<>();
        Date date = new Date();
        HashMap<String, Object> extra = new HashMap<>();

        public AlertBuilder description(String desc) {
            Objects.requireNonNull(desc);
            description = desc;
            return this;
        }

        public AlertBuilder id(int alertId) {
            id = alertId;
            return this;
        }

        public AlertBuilder action(AlertAction action) {
            actions.add(action);
            return this;
        }

        public AlertBuilder date(Date d) {
            date = d;
            return this;
        }

        public AlertBuilder extra(String key, Object val) {
            extra.putIfAbsent(key, val);
            return this;
        }


        public Alert build() {
            return new AlertImp(this);
        }
    }

}
