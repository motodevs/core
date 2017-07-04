package net.motodev.core;


import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import net.motodev.core.db.Collection;
import net.motodev.core.db.DeviceQueryHelper;
import net.motodev.core.db.Persistor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by oksuz on 19/05/2017.
 */
public class Motodev {

    private final DeviceRegistry deviceRegistry = new DeviceRegistry();
    private final JsonObject config;
    private final long FIVE_MIN_IN_MILIS = MILLISECONDS.convert(5, MINUTES);
    private Vertx vertx;
    private Persistor persistor;
    private static Motodev motodevInstance;
    private ScheduledExecutorService metaUpdater = Executors.newSingleThreadScheduledExecutor();
    private static final Logger LOGGER = LoggerFactory.getLogger(Motodev.class);

    public static Motodev create(JsonObject config) {
        if (null == motodevInstance) {
            motodevInstance = new Motodev(config);
        }
        return motodevInstance;
    }

    public static Motodev getInstance() {
        if (null == motodevInstance) {
            throw new RuntimeException("Motodev instance does not created");
        }

        return motodevInstance;
    }

    private Motodev(JsonObject config) {
        Objects.requireNonNull(config);

        this.config = config;
        createPersistor(config);
        startMetaUpdater();
    }

    private void startMetaUpdater() {
        metaUpdater.scheduleAtFixedRate(() -> {
            try {
                LOGGER.info("meta updater starting");
                MongoClient client = newDbClient();
                JsonObject query = new JsonObject();
                query.put("status", DeviceStatus.MOVING);
                query.put("updatedAt", new JsonObject().put("$lt", (new Date().getTime() - FIVE_MIN_IN_MILIS)));

                client.find(Collection.DEVICE_META, query, result -> {
                    if (result.succeeded()) {
                        List<JsonObject> metas = result.result();
                        for (JsonObject meta : metas) {
                            if (meta.containsKey("deviceId") && !Objects.equals(meta.getString("deviceId"), "")) {
                                meta.put("status", DeviceStatus.CONNECTION_LOST);
                                meta.put("updatedAt", new Date().getTime());
                                DeviceQueryHelper deviceQueryHelper = new DeviceQueryHelper(meta.getString("deviceId"), getPersistor());
                                deviceQueryHelper.upsertMeta(meta, new JsonObject());
                            }
                        }
                    }
                    client.close();
                });

            } catch (Exception e) {
                LOGGER.error("Device meta updater exception", e);
            }
        }, 0, 5, TimeUnit.MINUTES);
    }

    private void createPersistor(JsonObject config) {
        persistor = new Persistor(config.getJsonObject("database").getJsonObject("mongodb"), getVertx());
    }

    public Persistor getPersistor() {
        return persistor;
    }

    public MongoClient newDbClient() {
        return getPersistor().newClient();
    }

    public void deployVerticle(Class<?> verticle, DeploymentOptions deploymentOptions) {
        DeploymentOptions opts = new DeploymentOptions(deploymentOptions);
        opts.setConfig(config);

        Consumer<Vertx> runner = vertx -> {
            try {
                vertx.deployVerticle(verticle.getName(), opts);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        };

        runner.accept(getVertx());
    }


    public DeviceRegistry getDeviceRegistry() {
        return deviceRegistry;
    }

    public JsonObject getConfig() {
        return new JsonObject(config.toString());
    }

    public Vertx getVertx() {
        if (null == vertx) {
            vertx = Vertx.vertx(new VertxOptions());
        }

        return vertx;
    }

    public static class Constant {
        public static final String NEW_MESSAGE = "NEW_MESSAGE";
        public static final String DEVICE_COMMAND = "DEVICE_COMMAND";
        public static final String PERSIST = "PERSIST";
        public static final String ALARM = "ALARM";
    }
}
