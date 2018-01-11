package com.openvehicletracking.core;

import com.openvehicletracking.core.protocol.MessagingProtocol;

import javax.annotation.Nullable;


public interface ProtocolChain {

    ProtocolChain next(@Nullable MessagingProtocol protocol);

    void handle(Object message);
}
