package com.openvehicletracking.core.protocol;

import com.openvehicletracking.core.ConnectionHolder;

public interface ProtocolChain {

    void add(MessagingProtocol protocol);

    Message handle(Object message, ConnectionHolder<?> connectionHolder);

    MessagingProtocol find(String name);
}
