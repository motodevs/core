package net.motodev.core;


import io.vertx.ext.mongo.MongoClient;

/**
 * Created by oksuz on 19/05/2017.
 */
public interface Message {

    String subject();

    void save(MongoClient client, String collection, Callback<Object> callback);

    String device();

    String deviceId();
}
