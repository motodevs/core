package com.openvehicletracking.core;

import com.openvehicletracking.core.protocol.Command;
import com.openvehicletracking.core.protocol.MessagingProtocol;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public abstract class AbstractCommandMessageBuilder<T extends Command> implements MessageBuilder<T> {

    private MessagingProtocol protocol;
    private HashMap<String, Object> attributes = new HashMap<>();
    private Date date;
    private Object raw;
    private String requestId;

    public AbstractCommandMessageBuilder requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    @Override
    public MessageBuilder raw(Object raw) {
        this.raw = raw;
        return this;
    }

    @Override
    public MessageBuilder attribute(String key, Object value) {
        attributes.put(key, value);
        return this;
    }

    @Override
    public MessageBuilder date(Date date) {
        this.date = date;
        return this;
    }

    @Override
    public MessageBuilder protocol(MessagingProtocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public MessagingProtocol getProtocol() {
        return protocol;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public Date getDate() {
        return date;
    }

    public Object getRaw() {
        return raw;
    }

    public Optional<String> getRequestId() {
        return Optional.ofNullable(this.requestId);
    }

}
