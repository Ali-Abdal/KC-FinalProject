package com.example.finalproject;

public class Hospitals {
    private String name;
    private int phonenum;

    public Hospitals(String name, int phonenum) {
        this.name = name;
        this.phonenum = phonenum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(int phonenum) {
        this.phonenum = phonenum;
    }
}
