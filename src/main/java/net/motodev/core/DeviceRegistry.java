package net.motodev.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by oksuz on 19/05/2017.
 */
public class DeviceRegistry {

    private List<Device> devices = new ArrayList<>();

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

    public List<Device> getDevices() {
        return devices;
    }
}
