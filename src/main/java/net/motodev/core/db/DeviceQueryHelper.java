package net.motodev.core.db;

import com.google.gson.Gson;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;
import net.motodev.core.alarm.Alarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by yo on 14/06/2017.
 */
public class DeviceQueryHelper {

    private String deviceId;
    private Persistor persistor;

    public DeviceQueryHelper(String deviceId, Persistor persistor) {
        Objects.requireNonNull(deviceId);
        Objects.requireNonNull(persistor);
        this.persistor = persistor;
        this.deviceId = deviceId;
    }

    public void readMeta(Handler<Object> handler) {
        Objects.requireNonNull(handler);

        MongoClient client = persistor.newClient();

        client.find(Collection.DEVICE_META, getQuery(), result -> {
            if (result.succeeded()) {
                handler.handle(result.result());
            }

            client.close();
        });
    }

    public void upsertMeta(JsonObject document, JsonObject query){
        Objects.requireNonNull(document);

        MongoClient client = persistor.newClient();
        client.replaceDocumentsWithOptions(Collection.DEVICE_META, getQuery(query), document, new UpdateOptions(true), r -> client.close());
    }

    public void readAlarms(Handler<List<Alarm>> handler, int limit, JsonObject query) {
        Objects.requireNonNull(handler);

        MongoClient client = persistor.newClient();
        FindOptions findOptions = new FindOptions().setSort(new JsonObject().put("datetime", -1)).setLimit(limit);

        if (query == null) {
            query = new JsonObject();
        }

        client.findWithOptions(Collection.ALARMS, query, findOptions, alarmsResultHandler(client, handler));
    }

    public void readAlarms(Handler<List<Alarm>> handler, int limit) {
        readAlarms(handler, limit, null);
    }


    private Handler<AsyncResult<List<JsonObject>>> alarmsResultHandler(MongoClient client, Handler<List<Alarm>> handler) {
        Gson gson = new Gson();
        List<Alarm> alarms = new ArrayList<>();
        return result -> {
            if (result.succeeded()) {
                result.result().forEach(jsonObject -> alarms.add(0, gson.fromJson(jsonObject.toString(), Alarm.class)));
                handler.handle(alarms);
            }

            client.close();
        };
    }

    private JsonObject getQuery(JsonObject query) {
        return query == null ? getQuery() : query.put("deviceId", this.deviceId);
    }

    private JsonObject getQuery() {
        return new JsonObject().put("deviceId", this.deviceId);
    }


}
