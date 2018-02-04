package com.openvehicletracking.core.protocol;


import com.openvehicletracking.core.ConnectionHolder;

/**
 * Created by oksuz on 19/05/2017.
 *
 */
public interface MessageHandler {

    boolean isMatch(Object msg);

    Message handle(Object msg, ConnectionHolder<?> connectionHolder);

}
