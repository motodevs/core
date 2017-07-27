package com.openvehicletracking.core.db;

import com.google.gson.Gson;
import com.openvehicletracking.core.message.Message;
import io.vertx.core.json.JsonObject;

import java.util.Date;

/**
 * Created by oksuz on 20/07/2017.
 *
 */
public class MessagesDAO extends AbstractDAO {

    public MessagesDAO(DBClientFactory factory) {
        super(factory);
    }

    public void save(Message message) {
        JsonObject body = new JsonObject(new Gson().toJson(message));
        body.put("createdAt", new Date().getTime());
        save(Collection.MESSAGES, body, emptyHandler(), emptyFailedQueryHandler());
    }
}
