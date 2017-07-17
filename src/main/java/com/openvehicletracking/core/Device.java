package com.openvehicletracking.core;

import com.openvehicletracking.core.alarm.Alarm;
import com.openvehicletracking.core.message.MessageHandler;
import com.openvehicletracking.core.message.Message;
import io.vertx.core.Handler;
import com.openvehicletracking.core.adapter.ResponseAdapter;
import com.openvehicletracking.core.db.DeviceQueryHelper;

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
