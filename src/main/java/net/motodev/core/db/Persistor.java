package net.motodev.core.db;

import com.google.gson.Gson;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import net.motodev.core.alarm.Alarm;
import net.motodev.core.message.Message;
import net.motodev.core.utility.DateUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by yo on 03/06/2017.
 */
public class Persistor {

    private JsonObject credentials;
    private Vertx vertx;

    private static final Logger LOGGER = LoggerFactory.getLogger(Persistor.class);

    public Persistor(JsonObject credentials, Vertx vertx) {
        this.credentials = credentials;
        this.vertx = vertx;
    }

    public MongoClient newClient() {
        return MongoClient.createNonShared(vertx, credentials);
    }

    public void saveMessage(Message m) {
        MongoClient mongoClient = newClient();
        JsonObject body = new JsonObject(Json.encode(m));
        body.put("datetime", new JsonObject().put("$date", DateUtility.toISODateFormat(m.messageDate())));
        body.put("createdAt", new JsonObject().put("$date", DateUtility.toISODateFormat(new Date())));
        body.put("messageType", m.type());
        body.put("deviceType", m.device());

        mongoClient.insert(Collection.MESSAGES, body, result -> {
            if (result.failed()) {
                LOGGER.error("An error occurred while saving user message deviceId " + m.deviceId(), result.cause());
            }

            mongoClient.close();
        });
    }

    public void updateCommand(Message message) {
        JsonObject query = new JsonObject();
        query.put("deviceId", message.deviceId());
        query.put("requestId", message.requestId());

        JsonObject update = new JsonObject();
        JsonObject $set = new JsonObject();
        JsonObject deviceResponse = new JsonObject();

        if (null != message.extraParameters()) {
            deviceResponse.put("params", new JsonArray(Arrays.asList(message.extraParameters())));
        }

        deviceResponse.put("responseTime", new JsonObject().put("$date", DateUtility.toISODateFormat(message.messageDate())));

        $set.put("response", deviceResponse);
        $set.put("read", true);

        update.put("$set", $set);

        MongoClient mongoClient = newClient();
        mongoClient.updateCollection(Collection.COMMANDS, query, update, result -> mongoClient.close());
    }


    public void createAlarm(Alarm alarm) {
        MongoClient client = newClient();
        JsonObject alarmAsJson = new JsonObject(new Gson().toJson(alarm));
        alarmAsJson.put("date", new JsonObject().put("$date", DateUtility.toISODateFormat(alarm.date())));
        client.insert(Collection.ALARMS, alarmAsJson, result -> client.close());
    }


}
