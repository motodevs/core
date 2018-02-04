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

    public CellTower() {
    }

    public CellTower(short mcc, byte mnc, short lac, int cellTower) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.lac = lac;
        this.cellTower = cellTower;
    }

    public void setMcc(short mcc) {
        this.mcc = mcc;
    }

    public void setMnc(short mnc) {
        this.mnc = mnc;
    }

    public void setLac(short lac) {
        this.lac = lac;
    }

    public void setCellTower(int cellTower) {
        this.cellTower = cellTower;
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
