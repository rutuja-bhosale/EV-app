package com.example.charging_station_app;

public class My {

    String name,address,number,tslot,fslot,imageurl;

    public My() {
    }

    public My(String name,String address, String number,String tslot,String fslot, String imageurl) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.tslot= tslot;
        this.fslot=fslot;
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


    public String getTslot(){return tslot;}

    public String setTslot(String tslot){
        this.tslot = tslot;
        return tslot;

    }

    public String getFslot() {
        return getFslot();
    }
    public String setFslot(String fslot){
        this.fslot = fslot;
        return fslot;
    }


    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


}
