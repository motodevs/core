package net.motodev.core;

/**
 * Created by oksuz on 14/05/2017.
 *
 */
public interface ResponseAdapter<U, V> {

    public U result(V object);

}
