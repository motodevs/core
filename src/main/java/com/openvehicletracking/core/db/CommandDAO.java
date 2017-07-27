package com.openvehicletracking.core.db;

import com.openvehicletracking.core.exception.MandatoryFieldException;
import com.openvehicletracking.core.message.Message;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import static java.util.Objects.requireNonNull;

/**
 * Created by oksuz on 20/07/2017.
 *
 */
public class CommandDAO extends AbstractDAO {

    private String deviceId;

    public CommandDAO(DBClientFactory factory, String deviceId) {
        super(factory);
        requireNonNull(deviceId, "device id cannot be empty");
        this.deviceId = deviceId;
    }

    public void update(Message message) throws MandatoryFieldException {
        JsonObject query = new JsonObject();

        if (!message.getRequestId().isPresent()) {
            throw new MandatoryFieldException("RequestId required for update command");
        }

        query.put("deviceId", deviceId);
        query.put("requestId", message.getRequestId().get());

        JsonObject update = new JsonObject();
        JsonObject $set = new JsonObject();
        JsonObject deviceResponse = new JsonObject();

        if (null != message.getExtraParameters()) {
            deviceResponse.put("params", message.getExtraParameters());
        }

        deviceResponse.put("responseTime", message.getDatetime());
        $set.put("response", deviceResponse);
        $set.put("read", true);
        update.put("$set", $set);

        updateOne(Collection.COMMANDS, query, update, emptyHandler(), emptyFailedQueryHandler());
    }

    public void getUnread(Handler<JsonArray> handler) {
        JsonObject query = new JsonObject().put("deviceId", deviceId).put("read", false);
        findAll(Collection.COMMANDS, query, records -> {
            JsonArray toHandle = new JsonArray();

            records.forEach(record -> {
                String command = record.getString("command", "");
                if (!"".equals(command)) {
                    toHandle.add(command);
                }
            });

            handler.handle(toHandle);
        }, emptyFailedQueryHandler());
    }

}
