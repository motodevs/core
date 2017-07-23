package com.openvehicletracking.core.db;

/**
 * Created by oksuz on 22/07/2017.
 *
 */
public class FailedQuery {

    private Throwable throwable;
    private String cause;

    public FailedQuery(Throwable throwable, String cause) {
        this.throwable = throwable;
        this.cause = cause;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public String getCause() {
        return cause;
    }
}
