package com.openmts.core.message;


/**
 * Created by oksuz on 19/05/2017.
 */
public interface Message {

    String requestId();

    String device();

    String deviceId();

    String type();

    boolean isCommand();

    long datetime();

    String[] extraParameters();
}
