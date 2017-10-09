package com.openvehicletracking.core.message;

import java.util.List;

/**
 * Created by oksuz on 30/08/2017.
 *
 */
public interface Reply<T> {

    List<T> get();

}
