package net.motodev.core;


import java.util.Date;

/**
 * Created by oksuz on 19/05/2017.
 */
public interface Message {

    String requestId();

    String device();

    String deviceId();

    String type();

    boolean isCommand();

    Date messageDate();

    String[] extraParameters();
}
