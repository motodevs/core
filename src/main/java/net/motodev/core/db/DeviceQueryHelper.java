package net.motodev.core.db;

import com.google.gson.Gson;
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
        JsonObject query = new JsonObject().put("deviceId", deviceId);

        client.find(Collection.DEVICE_META, query, r -> {
            if (r.succeeded()) {
                handler.handle(r.result());
            }

            client.close();
        });
    }

    public void upsertMeta(JsonObject document, JsonObject query){
        Objects.requireNonNull(document);
        Objects.requireNonNull(query);

        MongoClient client = persistor.newClient();
        query.put("deviceId", this.deviceId);
        UpdateOptions updateOptions = new UpdateOptions(true);
        client.replaceDocumentsWithOptions(Collection.DEVICE_META, query, document, updateOptions, r -> client.close());
    }

    public void readAlarms(Handler<List<Alarm>> handler, int limit) {
        MongoClient client = persistor.newClient();
        JsonObject query = new JsonObject().put("deviceId", this.deviceId);

        FindOptions findOptions = new FindOptions();
        findOptions.setSort(new JsonObject().put("date", -1));
        findOptions.setLimit(limit);

        List<Alarm> alarms = new ArrayList<>();
        client.findWithOptions(Collection.ALARMS, query, findOptions, result -> {
            Gson gson = new Gson();
            if (result.succeeded()) {
                result.result().forEach(jsonObject -> {
                    // @FIXME find better way de/serialize mongo ISODates
                    jsonObject.put("date", jsonObject.getJsonObject("date").getString("$date"));
                    alarms.add(0, gson.fromJson(jsonObject.toString(), Alarm.class));

                });

                handler.handle(alarms);
            }

            client.close();
        });

    }


}
