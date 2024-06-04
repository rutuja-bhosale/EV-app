package com.example.charging_station_app;

import java.util.Enumeration;

public class data {

    String name,address,number,tslot,fslot,imageurl;

    public data() {
    }

    public data(String name, String address, String number, String tslot, String fslot, String imageurl) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.tslot = tslot;
        this.fslot = fslot;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTslot() {
        return tslot;
    }

    public void setTslot(String tslot) {
        this.tslot = tslot;
    }

    public String getFslot() {
        return fslot;
    }

    public void setFslot(String fslot) {
        this.fslot = fslot;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
