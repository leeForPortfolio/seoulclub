package com.example.lkoon.seoulclub.model;

/**
 * Created by lkoon on 2017-10-16.
 */

public class Concern {
    int cno;
    String name;

    @Override
    public String toString() {
        return "Concern{" +
                "cno=" + cno +
                ", name='" + name + '\'' +
                '}';
    }

    public Concern(int cno, String name) {
        this.cno = cno;
        this.name = name;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
