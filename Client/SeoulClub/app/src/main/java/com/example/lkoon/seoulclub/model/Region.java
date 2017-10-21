package com.example.lkoon.seoulclub.model;

/**
 * Created by lkoon on 2017-10-15.
 */

public class Region {
    int lno;
    String region;

    public Region(int lno, String region) {
        this.lno = lno;
        this.region = region;
    }

    public int getLno() {
        return lno;
    }

    public void setLno(int lno) {
        this.lno = lno;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
