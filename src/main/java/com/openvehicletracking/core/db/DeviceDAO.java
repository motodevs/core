package com.openvehicletracking.core.db;

import com.openvehicletracking.core.alarm.Alarm;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created by oksuz on 14/06/2017.
 *
 */
public class DeviceDAO extends AbstractDAO {

    private String deviceId;
    private AlarmDAO alarmDAO;

    public DeviceDAO(DBClientFactory factory) {
        super(factory);
    }

    public DeviceDAO(DBClientFactory dbClientFactory, String deviceId) {
        super(dbClientFactory);
        this.deviceId = deviceId;
    }

    public DeviceDAO(DBClientFactory dbClientFactory, String deviceId, AlarmDAO alarmDAO) {
        super(dbClientFactory);
        this.deviceId = deviceId;
        this.alarmDAO = alarmDAO;
    }


    public void readMeta(Handler<JsonObject> handler) {
        requireNonNull(handler, "handler cannot be null");
        findOne(Collection.DEVICE_META, getQuery(), handler, emptyFailedQueryHandler());
    }

    public void upsertMeta(JsonObject document, JsonObject query){
        requireNonNull(document, "document cannot be null");
        replace(Collection.DEVICE_META, getQuery(query), document, emptyHandler(), emptyFailedQueryHandler());
    }

    public void readAlarms(Handler<List<Alarm>> handler, int limit, JsonObject query) {
        requireNonNull(handler);
        alarmDAO.findWithOrder("datetime", FindOrder.DESC, limit, getQuery(query), handler, emptyFailedQueryHandler());
    }

    public void readAlarms(Handler<List<Alarm>> handler, int limit) {
        readAlarms(handler, limit, null);
    }


    private JsonObject getQuery(JsonObject query) {
        return query == null ? getQuery() : query.put("deviceId", this.deviceId);
    }

    private JsonObject getQuery() {
        return new JsonObject().put("deviceId", this.deviceId);
    }


}
