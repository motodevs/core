package net.motodev.core;

/**
 * Created by oksuz on 20/05/2017.
 */
@FunctionalInterface
public interface Callback<T> {

    void call(T obj);

}
