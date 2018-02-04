package com.openvehicletracking.core.protocol;

import java.util.ArrayList;

public interface MessagingProtocol {

    /**
     *
     * @return protocol name
     */
    String getName();

    /**
     *
     * @return MessageHandler
     */
    ArrayList<MessageHandler> getHandlers();

}
