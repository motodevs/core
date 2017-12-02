package com.openvehicletracking.core.message;

import com.google.gson.JsonArray;
import com.openvehicletracking.core.JsonSerializeable;

import java.util.Optional;

/**
 * Created by oksuz on 19/05/2017.
 *
 * A generic Tracking Device Message
 */
public interface Message extends JsonSerializeable {

    /**
     * in generally server and tracking devices are talking with requestId,
     * Server sends command with requestId and tracking device replies with same requestId
     * @return requestId
     */
    Optional<String> getRequestId();

    /**
     * Get Device make as string
     * @return deviceName
     */
    String getDevice();

    /**
     * gets deviceId
     * @return deviceId
     */
    String getDeviceId();

    /**
     * tracking devices has different message types
     * @return message type
     */
    Optional<String> getType();

    /**
     * is command message or different message
     * @return isCommand
     */
    boolean isCommand();

    /**
     * @return epoch time
     */
    long getDatetime();

    /**
     * @return extra parameters
     */
    Optional<JsonArray> getExtraParameters();


    /**
     *
     * @return when return true then call Device#replyMessage
     */
    boolean isReplyRequired();

}
