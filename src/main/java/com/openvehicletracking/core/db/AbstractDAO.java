package com.openvehicletracking.core.db;


import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClientUpdateResult;
import io.vertx.ext.mongo.UpdateOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
/**
 * Created by oksuz on 20/07/2017.
 *
 */
class AbstractDAO {

    private final DBClientFactory factory;
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDAO.class);

    public AbstractDAO(DBClientFactory factory) {
        this.factory = factory;
    }

    public DBClient getClient() {
        return factory.newClient();
    }

    private <T> Handler<AsyncResult<T>> getGenericResultHandler(Handler<T> handler, Handler<FailedQuery> failedQueryHandler, DBClient client) {
        return result -> {
            if (result.failed() && failedQueryHandler != null) {
                failedQueryHandler.handle(new FailedQuery(result.cause(), result.cause().getMessage()));
                return;
            }

            handler.handle(result.result());
            client.close();
        };
    }

    public void findAll(String collection, JsonObject query, Handler<List<JsonObject>> handler, Handler<FailedQuery> failedQueryHandler) {
        DBClient client = getClient();
        if (query == null) {
            query = new JsonObject();
        }

        client.find(collection, query, getGenericResultHandler(handler, failedQueryHandler, client));
    }

    public void findOne(String collection, JsonObject query, Handler<JsonObject> handler, Handler<FailedQuery> failedQueryHandler) {
        DBClient client = getClient();
        client.findOne(collection, query, null, getGenericResultHandler(handler, failedQueryHandler, client));
    }

    public void findWithOptions(String collection, JsonObject query, FindOptions findOptions, Handler<List<JsonObject>> handler, Handler<FailedQuery> failedQueryHandler) {
        if (query == null) {
            query = new JsonObject();
        }

        DBClient client = getClient();
        client.findWithOptions(collection, query, findOptions, getGenericResultHandler(handler, failedQueryHandler, client));
    }

    public void save(String collection, JsonObject data, Handler<String> handler, Handler<FailedQuery> failedQueryHandler) {
        DBClient client = getClient();
        client.insert(collection, data, getGenericResultHandler(handler, failedQueryHandler, client));
    }

    public void updateOne(String collection, JsonObject query, JsonObject data, Handler<MongoClientUpdateResult> handler, Handler<FailedQuery> failedQueryHandler) {
        DBClient client = getClient();
        client.updateCollection(collection, query, data, getGenericResultHandler(handler, failedQueryHandler, client));
    }

    public void updateMulti(String collection, JsonObject query, JsonObject data, Handler<MongoClientUpdateResult> handler, Handler<FailedQuery> failedQueryHandler) {
        DBClient client = getClient();
        UpdateOptions updateOptions = new UpdateOptions().setMulti(true);
        client.updateCollectionWithOptions(collection, query, data, updateOptions, getGenericResultHandler(handler, failedQueryHandler, client));
    }

    public void upsert(String collection, JsonObject query, JsonObject data, Handler<MongoClientUpdateResult> handler, Handler<FailedQuery> failedQueryHandler) {
        DBClient client = getClient();
        UpdateOptions updateOptions = new UpdateOptions().setUpsert(true);
        client.updateCollectionWithOptions(collection, query, data, updateOptions, getGenericResultHandler(handler, failedQueryHandler, client));
    }

    public void replace(String collection, JsonObject query, JsonObject data, Handler<MongoClientUpdateResult> handler, Handler<FailedQuery> failedQueryHandler) {
        DBClient client = getClient();
        UpdateOptions updateOptions = new UpdateOptions().setUpsert(true);
        client.replaceDocumentsWithOptions(collection, query, data, updateOptions, getGenericResultHandler(handler, failedQueryHandler, client));
    }

    static <T> Handler<T> emptyHandler() {
        return result -> LOGGER.debug("Empty handler called result: {}", result);
    }

    static Handler<FailedQuery> emptyFailedQueryHandler() {
        return result -> LOGGER.error("Query error: " + result.getCause(), result.getThrowable());
    }
}
