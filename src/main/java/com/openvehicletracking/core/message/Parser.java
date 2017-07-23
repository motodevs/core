package com.openvehicletracking.core.message;

/**
 * Created by oksuz on 19/05/2017.
 * Parser interface
 */
public interface Parser {

    /**
     * @return parsed device message
     */
    Message parse();

}
