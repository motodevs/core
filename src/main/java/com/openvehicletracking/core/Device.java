package com.openvehicletracking.core;

import com.openvehicletracking.core.alarm.Alarm;
import com.openvehicletracking.core.geojson.GeoJsonResponse;
import com.openvehicletracking.core.message.*;
import com.openvehicletracking.core.message.exception.UnsupportedReplyTypeException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author oksuz
 *
 * Generic tracking device.
 */
public interface Device {

    /**
     * added to messages as deviceType
     * @return device name
     */
    String getName();

    /**
     * It provides tcp message handlers.
     * <p>
     *  when remote tracking device send message over http or tcp
     *  application try to find message handler for message calling this method.
     * </p>
     * @return Message handlers
     */
    CopyOnWriteArrayList<MessageHandler> getHandlers();

    /**
     *
     * @param message parsed device message
     * @return alarm
     */
    Alarm generateAlarmFromMessage(Message message);

    /**
     * update device meta or state using message
     * @param message parsed device message
     * @return device state
     */
    DeviceState createStateFromMessage(Message message);

    /**
     * this method calling when message received.
     *
     * if reply required for message or device have any unread commands in db,
     * you should call handler.handle method with json Array. message will reply with same tcp connection
     *
     * @param message device message
     * @param <T> type
     * @param unreadMessages messages of going wait to send
     * @return reply for message
     */
    <T> Reply<T> replyMessage(Message message, List<? extends CommandMessage> unreadMessages) throws UnsupportedReplyTypeException;

    /**
     *
     * @return locationMessage class
     */
    Class<? extends LocationMessage> getLocationType();

    /**
     *
     * @param messages message
     * @return geojson message
     */
    GeoJsonResponse responseAsGeoJson(List<? extends LocationMessage> messages);

    /**
     *
     * @return responseAdapter
     */
    ResponseAdapter getResponseAdapter();
}
