package com.openvehicletracking.core.message.impl;

import com.openvehicletracking.core.message.Reply;

import java.util.List;

/**
 * Created by oksuz on 05/09/2017.
 *
 */
public class StringReplies implements Reply<List<String>> {

    private final List<String> replies;

    public StringReplies(List<String> replies) {
        this.replies = replies;
    }

    @Override
    public List<String> get() {
        return null;
    }
}
