package net.motodev.core;

/**
 * Created by oksuz on 19/05/2017.
 */
public class MotodevCollector {

    private static final MotodevCollector instance = new MotodevCollector();
    private DeviceRegistry registry = new DeviceRegistry();

    public static MotodevCollector getInstance() {
        return instance;
    }

    private MotodevCollector() {}

    public DeviceRegistry deviceRegistry() {
        return registry;
    }

    public static class Constant {
        public static final String NEW_MESSAGE = "NEW_MESSAGE";
    }

}
