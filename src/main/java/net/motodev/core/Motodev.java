package net.motodev.core;


import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import net.motodev.core.db.Persistor;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by oksuz on 19/05/2017.
 */
public class Motodev {

    private final DeviceRegistry deviceRegistry = new DeviceRegistry();
    private final JsonObject config;
    private Vertx vertx;
    private Persistor persistor;
    private static Motodev motodevInstance;

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
    }

    private void createPersistor(JsonObject config) {
        persistor = new Persistor(config.getJsonObject("database").getJsonObject("mongodb"), getVertx());
    }

    public synchronized Persistor getPersistor() {
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


    public synchronized DeviceRegistry getDeviceRegistry() {
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
