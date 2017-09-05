package com.openvehicletracking.core.message.impl;

import com.openvehicletracking.core.message.Reply;

/**
 * Created by oksuz on 04/09/2017.
 *
 */
public class StringReply implements Reply<String> {

    private String msg;

    public StringReply(String msg) {
        this.msg = msg;
    }

    @Override
    public String get() {
        return msg;
    }
}
