package com.kpu.seoulclub.domain;

public class UserVO {
	private int uno;
	private String id;
	private String pwd;
	private String name;
	private String nickName;
	private String sex;
	private String introduce;
	private String birth;
	private String picturePath;
	private int lno;
	
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	@Override
	public String toString() {
		return "UserVO [uno=" + uno + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", nickName=" + nickName
				+ ", sex=" + sex + ", introduce=" + introduce + ", birth=" + birth + ", picturePath=" + picturePath
				+ ", lno=" + lno + "]";
	}
}
