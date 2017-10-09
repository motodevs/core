package com.openvehicletracking.core.message;

import java.util.regex.Pattern;

/**
 * Created by oksuz on 19/05/2017.
 *
 * Create message handlers using this interface. MessageHandlers are taken over by Device#getHandlers method
 */
public interface MessageHandler {

    /**
     * This method called when new incoming message.
     * when pattern matched with message then the handle method going to call
     * @return incoming message pattern
     */
    Pattern pattern();

    /**
     * @param msg message
     * @return device message
     */
    Message handle(String msg);

}
