package com.openvehicletracking.core;

import com.openvehicletracking.core.message.MessageHandler;
import io.vertx.core.AbstractVerticle;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;

/**
 * Created by oksuz on 21/05/2017.
 *
 */
abstract public class TrackerAbstractVerticle extends AbstractVerticle {


    protected MessageHandler findHandler(String message) {
        CopyOnWriteArrayList<Device> devices = OpenVehicleTracker.getInstance().getDeviceRegistry().getDevices();
        for (Device d : devices) {
            CopyOnWriteArrayList<MessageHandler> handlers = d.getHandlers();
            for (MessageHandler handler : handlers) {
                Matcher matcher = handler.pattern().matcher(message);
                if (matcher.matches()) {
                    return handler;
                }
            }
        }

        return null;
    }

}
