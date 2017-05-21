package net.motodev.core;

import java.util.Vector;

/**
 * Created by oksuz on 19/05/2017.
 */
public interface Device {

    String name();

    Vector<MessageHandler> handlers();

}
