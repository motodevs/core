package com.openmts.core;

import com.openmts.core.alarm.Alarm;
import com.openmts.core.message.MessageHandler;
import io.vertx.core.Handler;
import com.openmts.core.adapter.ResponseAdapter;
import com.openmts.core.db.DeviceQueryHelper;
import com.openmts.core.message.Message;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by oksuz on 19/05/2017.
 */
public interface Device {

    String name();

    CopyOnWriteArrayList<MessageHandler> handlers();

    ResponseAdapter messageResponseAdapter();

    void createAlarmIfRequired(Message m, DeviceQueryHelper deviceQueryHelper, Handler<Alarm> handler);

    void updateMeta(Message m, DeviceQueryHelper deviceQueryHelper);
}
