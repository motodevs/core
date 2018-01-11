package com.openvehicletracking.core.alert;

import com.openvehicletracking.core.json.JsonSerializeable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface Alert extends JsonSerializeable {

    String getDescription();

    int getAlertId();

    List<AlertAction> getActions();

    Date getAlertDate();

    HashMap<String, Object> getExtraData();

}
