package net.motodev.core;

import java.util.regex.Pattern;

/**
 * Created by oksuz on 19/05/2017.
 */
public interface MessageHandler<T> {

    public Pattern pattern();

    public Message handle(T obj);

}
