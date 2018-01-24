package com.openvehicletracking.core.protocol.impl;

import com.openvehicletracking.core.ConnectionHolder;
import com.openvehicletracking.core.protocol.Message;
import com.openvehicletracking.core.protocol.MessageHandler;
import com.openvehicletracking.core.protocol.MessagingProtocol;
import com.openvehicletracking.core.protocol.ProtocolChain;


import java.util.HashMap;
import java.util.Optional;


public class ProtocolChainImpl implements ProtocolChain {

    private final HashMap<String, MessagingProtocol> protocols = new HashMap<>();

    @Override
    public void add(MessagingProtocol protocol) {
        protocols.putIfAbsent(protocol.getName(), protocol);
    }

    @Override
    public Message handle(Object message, ConnectionHolder<?> connectionHolder) {
        for (MessagingProtocol protocol : protocols.values()) {
            Optional<MessageHandler> handler = protocol.getHandlers().stream().filter(messageHandler -> messageHandler.isMatch(message)).findFirst();
            if (handler.isPresent()) {
                return handler.get().handle(message, connectionHolder);
            }
        }
        return null;
    }

    @Override
    public MessagingProtocol find(String name) {
        return protocols.getOrDefault(name, null);
    }
}
