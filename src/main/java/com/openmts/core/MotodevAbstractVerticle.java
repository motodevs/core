package com.openmts.core;

import io.vertx.core.AbstractVerticle;
import com.openmts.core.message.MessageHandler;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;

/**
 * Created by yo on 21/05/2017.
 */
abstract public class MotodevAbstractVerticle extends AbstractVerticle {


    protected MessageHandler findHandler(String message) {
        CopyOnWriteArrayList<Device> devices = Motodev.getInstance().getDeviceRegistry().getDevices();
        for (Device d : devices) {
            CopyOnWriteArrayList<MessageHandler> handlers = d.handlers();
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
