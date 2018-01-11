package com.openvehicletracking.core.protocol;


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
    boolean isMatch(Object msg);

    /**
     * @param msg message
     * @return device message
     */
    Message handle(Object msg);

}
