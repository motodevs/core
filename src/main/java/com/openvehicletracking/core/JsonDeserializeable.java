package com.openvehicletracking.core;

/**
 * Created by yo on 15/11/2017.
 */
public interface JsonDeserializeable<T> {


    T fromJsonString(String json);

}
