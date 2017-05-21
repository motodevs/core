package net.motodev.core;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by oksuz on 19/05/2017.
 */
public class MotodevCollector {

    private static final MotodevCollector instance = new MotodevCollector();

    private final CopyOnWriteArrayList<Device> devices = new CopyOnWriteArrayList<>();

    public static MotodevCollector getInstance() {
        return instance;
    }

    private MotodevCollector() {}

    public void addDevice(Device device) {
        devices.add(device);
    }

    public Device getDevice(String name) {
        for (Device d : devices) {
            if (Objects.equals(d.name(), d.name())) {
                return d;
            }
        }

        return null;
    }

    public CopyOnWriteArrayList<Device> getDevices() {
        return devices;
    }


    public static class Constant {
        public static final String NEW_MESSAGE = "NEW_MESSAGE";
        public static final String DEVICE_COMMAND = "DEVICE_COMMAND";
        public static final String PERSIST = "PERSIST";
    }

}
