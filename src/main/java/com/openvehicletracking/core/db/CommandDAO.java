package com.openvehicletracking.core.db;

import com.openvehicletracking.core.exception.MandatoryFieldException;
import com.openvehicletracking.core.message.Message;
import io.vertx.core.json.JsonObject;

/**
 * Created by oksuz on 20/07/2017.
 *
 */
public class CommandDAO extends AbstractDAO {

    public CommandDAO(DBClientFactory factory) {
        super(factory);
    }

    public void update(Message message) throws MandatoryFieldException {
        JsonObject query = new JsonObject();

        if (!message.getRequestId().isPresent()) {
            throw new MandatoryFieldException("RequestId required for update command");
        }

        query.put("deviceId", message.getDevice());
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

}
