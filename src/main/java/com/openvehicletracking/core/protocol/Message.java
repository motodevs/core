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
    String ATTR_DISTANCE = "distance";
    String ATTR_CELL_TOWER = "cell_tower";
    String ATTR_IGN_KEY_ON = "ign_key_on";
    String ATTR_GPS_DATA_UPLOAD_MODE = "gps_data_upload_mode";
    String ATTR_REAL_TIME_UPLOAD = "gps_real_time_upload";
    String ATTR_NUMBER_OF_SATELLITES = "number_of_satellites";

    Object getRaw();

    Device getDevice();

    Date getDate();

    Optional<HashMap<String, Object>> getAttributes();

    void reply(Reply reply);
}
