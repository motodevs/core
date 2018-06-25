package com.openvehicletracking.core.exception;

public class StateCreateNotSupportException extends Exception {

    public StateCreateNotSupportException() {
    }

    public StateCreateNotSupportException(String message) {
        super(message);
    }

    public StateCreateNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
