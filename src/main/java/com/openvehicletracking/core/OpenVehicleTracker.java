package com.openvehicletracking.core;


import com.openvehicletracking.core.db.Collection;
import com.openvehicletracking.core.db.DBClientFactory;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import com.openvehicletracking.core.db.DeviceDAO;
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
 *
 */
public class OpenVehicleTracker {

    private final DeviceRegistry deviceRegistry = new DeviceRegistry();
    private final JsonObject config;
    private final long FIVE_MIN_IN_MILIS = MILLISECONDS.convert(5, MINUTES);
    private Vertx vertx;
    private DBClientFactory dbClientFactory;
    private static OpenVehicleTracker openVehicleTrackerInstance;
    private ScheduledExecutorService metaUpdater = Executors.newSingleThreadScheduledExecutor();
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenVehicleTracker.class);

    public static OpenVehicleTracker create(JsonObject config) {
        if (null == openVehicleTrackerInstance) {
            openVehicleTrackerInstance = new OpenVehicleTracker(config);
        }
        return openVehicleTrackerInstance;
    }

    public static OpenVehicleTracker getInstance() {
        if (null == openVehicleTrackerInstance) {
            throw new RuntimeException("OpenVehicleTracker instance does not created");
        }

        return openVehicleTrackerInstance;
    }

    private OpenVehicleTracker(JsonObject config) {
        Objects.requireNonNull(config);

        this.config = config;
        dbClientFactory = new DBClientFactory(config.getJsonObject("database").getJsonObject("mongodb"), getVertx());
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
                            DeviceState state = DeviceState.fromJson(meta);
                            if (state.getDeviceId() != null && !Objects.equals(state.getDeviceId(), "")) {
                                state.setDeviceStatus(DeviceStatus.CONNECTION_LOST);
                                state.setUpdatedAt(new Date().getTime());
                                DeviceDAO deviceDAO = new DeviceDAO(getDbClientFactory(), state.getDeviceId());
                                deviceDAO.upsertMeta(state, new JsonObject());
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

    public DBClientFactory getDbClientFactory() {
        return dbClientFactory;
    }

    public MongoClient newDbClient() {
        return getDbClientFactory().newClient();
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
