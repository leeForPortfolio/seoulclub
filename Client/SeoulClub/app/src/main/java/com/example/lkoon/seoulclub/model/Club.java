package com.example.lkoon.seoulclub.model;

/**
 * Created by lkoon on 2017-10-01.
 */

public class Club {
    private int cno = 0;
    private String name = "";
    private String introduce = "";
    private int currPeople = 0;
    private int lno = 0;
    private String lnoName = "";
    private int concern = 0;
    private String concernName = "";

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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getCurrPeople() {
        return currPeople;
    }

    public void setCurrPeople(int currPeople) {
        this.currPeople = currPeople;
    }

    public int getLno() {
        return lno;
    }

    public void setLno(int lno) {
        this.lno = lno;
    }

    public String getLnoName() {
        return lnoName;
    }

    public void setLnoName(String lnoName) {
        this.lnoName = lnoName;
    }

    public int getConcern() {
        return concern;
    }

    public void setConcern(int concern) {
        this.concern = concern;
    }

    public String getConcernName() {
        return concernName;
    }

    public void setConcernName(String concernName) {
        this.concernName = concernName;
    }
}
