package net.motodev.core.message;

import java.util.regex.Pattern;

/**
 * Created by oksuz on 19/05/2017.
 */
public interface MessageHandler<T> {

    Pattern pattern();

    Message handle(T obj);

}
