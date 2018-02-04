package com.openvehicletracking.core;


import com.openvehicletracking.core.json.GsonFactory;
import com.openvehicletracking.core.json.JsonSerializeable;

public class Position implements JsonSerializeable {

    private double latitude;
    private double longitude;
    private double direction;
    private int speed;
    private CourseAndStatus courseAndStatus;
    private CellTower cellTower;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public CourseAndStatus getCourseAndStatus() {
        return courseAndStatus;
    }

    public void setCourseAndStatus(CourseAndStatus courseAndStatus) {
        this.courseAndStatus = courseAndStatus;
    }

    public CellTower getCellTower() {
        return cellTower;
    }

    public void setCellTower(CellTower cellTower) {
        this.cellTower = cellTower;
    }

    @Override
    public String asJson() {
        return GsonFactory.getGson().toJson(this);
    }
}
