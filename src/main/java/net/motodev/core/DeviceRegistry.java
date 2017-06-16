package net.motodev.core;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yo on 03/06/2017.
 */
public class DeviceRegistry {

    private final CopyOnWriteArrayList<Device> devices = new CopyOnWriteArrayList<>();

    public void register(Device device) {
        devices.add(device);
    }

    public Device findDevice(String name) {
        for (Device d : devices) {
            if (Objects.equals(name, d.name())) {
                return d;
            }
        }

        return null;
    }

    public CopyOnWriteArrayList<Device> getDevices() {
        return devices;
    }
}
