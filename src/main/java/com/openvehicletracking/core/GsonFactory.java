package com.openvehicletracking.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by oksuz on 15/11/2017.
 *
 */
public class GsonFactory {

    public static Gson newGson() {
        return new GsonBuilder().serializeNulls().create();
    }

}
