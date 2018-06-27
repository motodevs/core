package com.openvehicletracking.core;

public class CacheKeyGenerator {

    public static final String STATE = "state_%s";

    public static String stateCacheKey(String deviceId) {
        if (null != deviceId && !deviceId.trim().equals("")) {
            return String.format(STATE, deviceId);
        }

        throw new IllegalArgumentException("device id connot be null or empty");
    }

}
