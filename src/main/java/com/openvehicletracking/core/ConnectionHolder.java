package com.openvehicletracking.core;

public interface ConnectionHolder<T> {

    void write(Reply reply);

    T getConnection();

}
