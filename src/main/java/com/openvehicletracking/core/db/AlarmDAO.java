package com.openvehicletracking.core.db;

import com.google.gson.Gson;
import com.openvehicletracking.core.alarm.Alarm;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oksuz on 20/07/2017.
 *
 */
public class AlarmDAO extends AbstractDAO {

    public AlarmDAO(DBClientFactory factory) {
        super(factory);
    }

    public void save(Alarm alarm) {
        super.save(Collection.ALARMS, new JsonObject(new Gson().toJson(alarm)), emptyHandler(), emptyFailedQueryHandler());
    }

    public void find(JsonObject query, FindOptions findOptions, Handler<List<Alarm>> handler, Handler<FailedQuery> failedQueryHandler) {
        if (null == findOptions) {
            findAll(Collection.ALARMS, query, convertHandler(handler), failedQueryHandler);
        } else {
            findWithOptions(Collection.ALARMS, query, findOptions, convertHandler(handler), failedQueryHandler);
        }
    }

    public void find(JsonObject query, Handler<List<Alarm>> handler, Handler<FailedQuery> failedQueryHandler) {
        find(query, null, handler, failedQueryHandler);
    }

    public void findWithOrder(String field, FindOrder findOrder, int limit, JsonObject query, Handler<List<Alarm>> handler, Handler<FailedQuery> failedQueryHandler) {
        FindOptions findOptions = new FindOptions();
        findOptions.setSort(new JsonObject().put(field, findOrder.getValue())).setLimit(limit);
        find(query, findOptions, handler, failedQueryHandler);
    }

    private Handler<List<JsonObject>> convertHandler(Handler<List<Alarm>> convertedHandler) {
        List<Alarm> alarms = new ArrayList<>();
        Gson gson = new Gson();
        return result -> {
            result.forEach(jsonObject -> alarms.add(0, gson.fromJson(jsonObject.toString(), Alarm.class)));
            convertedHandler.handle(alarms);
        };
    }
}
