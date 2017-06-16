package net.motodev.core;

import io.vertx.core.Handler;
import net.motodev.core.adapter.ResponseAdapter;
import net.motodev.core.alarm.Alarm;
import net.motodev.core.db.DeviceQueryExecutor;
import net.motodev.core.message.Message;
import net.motodev.core.message.MessageHandler;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by oksuz on 19/05/2017.
 */
public interface Device {

    String name();

    CopyOnWriteArrayList<MessageHandler> handlers();

    ResponseAdapter messageResponseAdapter();

    void createAlarmIfRequired(Message m, DeviceQueryExecutor executor, Handler<Alarm> handler);

    void updateMeta(Message m, DeviceQueryExecutor executor);
}
