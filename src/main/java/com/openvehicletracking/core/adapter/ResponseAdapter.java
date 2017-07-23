package com.openvehicletracking.core.adapter;

/**
 * Created by oksuz on 14/05/2017.
 *
 * It uses time series location message output for REST
 */
public interface ResponseAdapter<U, V> {

    /**
     *
     * @param input input
     * @return output
     */
    public U result(V input);

}
