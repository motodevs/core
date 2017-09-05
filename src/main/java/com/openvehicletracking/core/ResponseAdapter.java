package com.openvehicletracking.core;

import com.openvehicletracking.core.message.Message;

import java.util.List;

/**
 * Created by oksuz on 14/05/2017.
 *
 * It uses time series location message output for REST
 */
public interface ResponseAdapter<U> {

    /**
     *
     * @param input input
     * @return output
     */
    public U result(List<? extends Message> input);

}
