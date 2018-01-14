package com.openvehicletracking.core.protocol;

import com.openvehicletracking.core.Device;
import com.openvehicletracking.core.Reply;
import com.openvehicletracking.core.json.JsonSerializeable;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by oksuz on 19/05/2017.
 *
 */
public interface Message extends JsonSerializeable {

    String ATTR_ALERT = "alert";

    Object getRaw();

    Device getDevice();

    Date getDate();

    Optional<HashMap<String, Object>> getAttributes();

    void reply(Reply reply);
}
