package com.openvehicletracking.core.db;

/**
 * Created by yo on 03/06/2017.
 */
public enum MongoCollection {

    MESSAGES("messages"),
    USERS("users"),
    COMMANDS("commands"),
    DEVICE_META("device_meta"),
    ALARMS("alarms");

    private final String col;

   MongoCollection(String col) {
        this.col = col;
    }

   public String getName() {
        return col;
   }

}
