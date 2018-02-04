package com.openvehicletracking.core.protocol;

import com.openvehicletracking.core.ConnectionHolder;
import java.util.Optional;

public interface Command extends Message {

    Optional<String> getRequestId();

    <T> void send(ConnectionHolder<T> connectionHolder);
}
