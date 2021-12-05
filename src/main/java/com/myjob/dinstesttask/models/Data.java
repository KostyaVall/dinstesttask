package com.myjob.dinstesttask.models;

import java.util.Date;

public class Data {

    private int id;
    private String name;
    private Date timeStamp;

    public Data() {

    }

    public Data(int id, String name, Date timeStamp) {
        this.id = id;
        this.name = name;
        this.timeStamp = timeStamp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {

        String str = "id: " + this.getId() + "; name: " + this.getName() + "; timeStamp: " + getTimeStamp();
        return str;
    }
}
