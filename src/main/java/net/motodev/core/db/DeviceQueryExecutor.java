package net.motodev.core.db;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;

import java.util.Objects;

/**
 * Created by yo on 14/06/2017.
 */
public class DeviceQueryExecutor {

    private String deviceId;
    private Persistor persistor;

    public DeviceQueryExecutor(String deviceId, Persistor persistor) {
        Objects.requireNonNull(deviceId);
        Objects.requireNonNull(persistor);
        this.persistor = persistor;
        this.deviceId = deviceId;
    }

    public synchronized void readMeta(Handler<Object> handler) {
        Objects.requireNonNull(handler);
        MongoClient client = persistor.newClient();
        JsonObject query = new JsonObject().put("deviceId", deviceId);

        client.find(Collection.DEVICE_META, query, r -> {
            if (r.succeeded()) {
                handler.handle(r.result());
            }

            if (r.failed()) {
                handler.handle(null);
            }

            client.close();
        });
    }

    public synchronized void upsertMeta(JsonObject update, JsonObject condition){
        Objects.requireNonNull(update);
        Objects.requireNonNull(condition);
        MongoClient client = persistor.newClient();
        condition.put("deviceId", this.deviceId);
        UpdateOptions updateOptions = new UpdateOptions();
        updateOptions.setUpsert(true);
        client.updateCollectionWithOptions(Collection.DEVICE_META, condition, update, updateOptions, r -> client.close());
    }
}
