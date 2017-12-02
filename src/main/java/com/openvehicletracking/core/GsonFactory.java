package com.openvehicletracking.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by oksuz on 15/11/2017.
 *
 */
public class GsonFactory {

    private static final Gson gsonInstance = new GsonBuilder().create();

    public static Gson getGson() {
        return gsonInstance;
    }

}
