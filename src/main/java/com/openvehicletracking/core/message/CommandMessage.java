package com.openvehicletracking.core.message;

/**
 * Created by oksuz on 05/09/2017.
 * interface for sending message to device
 */
public interface CommandMessage<T> extends Message {

    /**
     *
     * @return command to send to device
     */
    T getCommand();


    /**
     *
     * @return messages is read or not
     */
    boolean isRead();

}
