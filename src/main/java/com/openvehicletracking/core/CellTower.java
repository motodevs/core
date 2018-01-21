package com.openvehicletracking.core;

/**
 * https://developers.google.com/maps/documentation/geolocation/intro#cell_tower_object
 *
 */
public class CellTower {

    private short mcc;
    private short mnc;
    private short lac;
    private int cellTower;


    public CellTower(short mobileCountryCode, byte mobileNetworkCode, short locationAreaCode, int cellTowerId) {
        mcc = mobileCountryCode;
        mnc = mobileNetworkCode;
        lac = locationAreaCode;
        cellTower = cellTowerId;
    }

    public short getMcc() {
        return mcc;
    }

    public short getMnc() {
        return mnc;
    }

    public short getLac() {
        return lac;
    }

    public int getCellTower() {
        return cellTower;
    }
}
