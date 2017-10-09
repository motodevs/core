package com.openvehicletracking.core.message.impl;

import com.openvehicletracking.core.message.Reply;

import java.util.List;

/**
 * Created by yo on 23/09/2017.
 *
 */
public class ReplyImpl<T> implements Reply<T> {

    private List<T> data;

    public ReplyImpl(List<T> obj) {
        data = obj;
    }

    @Override
    public List<T> get() {
        return data;
    }
}
