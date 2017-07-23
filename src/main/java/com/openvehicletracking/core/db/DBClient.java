package com.openvehicletracking.core.db;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.impl.MongoClientImpl;

/**
 * Created by oksuz on 22/07/2017.
 *
 */
public class DBClient extends MongoClientImpl implements AutoCloseable {


    public DBClient(Vertx vertx, JsonObject config, String dataSourceName) {
        super(vertx, config, dataSourceName);
    }

    @Override
    public void close() {
        try {
            super.close();
        } catch (Exception ignored) {}
    }
}
