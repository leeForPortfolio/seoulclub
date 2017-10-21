package com.example.lkoon.seoulclub.model;

/**
 * Created by lkoon on 2017-10-02.
 */

public class User {
    int uno = 0;
    String name = "";
    String nickname ="";
    String sex ="";
    long birth =0;
    String pictutrePath ="";
    int lno = 0;
    String id ="";
    String pwd ="";
    String introduce;

    public User(String id) {
        this.id = id;
    }

    public User(String id, String pwd, String nickname, int lno, String sex, String introduce) {
        this.nickname = nickname;
        this.sex = sex;
        this.lno = lno;
        this.id = id;
        this.pwd = pwd;
        this.introduce =introduce;
    }

    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getBirth() {
        return birth;
    }

    public void setBirth(long birth) {
        this.birth = birth;
    }

    public String getPictutrePath() {
        return pictutrePath;
    }

    public void setPictutrePath(String pictutrePath) {
        this.pictutrePath = pictutrePath;
    }

    public int getLno() {
        return lno;
    }

    public void setLno(int lno) {
        this.lno = lno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
