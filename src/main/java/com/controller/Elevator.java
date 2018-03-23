package com.controller;

import java.math.BigDecimal;
import java.util.Objects;

public class Elevator {
    private String name;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elevator elevator = (Elevator) o;
        return Objects.equals(name, elevator.name) &&
                Objects.equals(longitude, elevator.longitude) &&
                Objects.equals(latitude, elevator.latitude);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, longitude, latitude);
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
