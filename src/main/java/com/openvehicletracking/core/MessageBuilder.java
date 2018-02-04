package com.openvehicletracking.core;

import com.openvehicletracking.core.protocol.Message;

import java.util.Date;

public interface MessageBuilder<T extends Message> {

    T build(Object... args);

    MessageBuilder raw(Object raw);

    MessageBuilder attribute(String key, Object value);

    MessageBuilder date(Date date);

    MessageBuilder protocol(String protocol);
}
