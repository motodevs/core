package com.openvehicletracking.core.db;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

/**
 * Created by oksuz on 03/06/2017.
 *
 */
public class DBClientFactory {

    private JsonObject credentials;
    private Vertx vertx;

    public DBClientFactory(JsonObject credentials, Vertx vertx) {
        this.credentials = credentials;
        this.vertx = vertx;
    }

    public DBClient newClient() {
        return new DBClient(vertx, credentials, UUID.randomUUID().toString());
    }
}
