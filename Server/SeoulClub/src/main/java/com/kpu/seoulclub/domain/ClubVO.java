package com.kpu.seoulclub.domain;

public class ClubVO {
	private int cno;
	private String name;
	private String description;
	private String introduce;
	private String picturePath;
	private int maxPeople;
	private int isprivate;
	private int isadmission;
	private int lno;
	private int concern;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	public int getIsprivate() {
		return isprivate;
	}
	public void setIsprivate(int isprivate) {
		this.isprivate = isprivate;
	}
	public int getIsadmission() {
		return isadmission;
	}
	public void setIsadmission(int isadmission) {
		this.isadmission = isadmission;
	}
	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public int getConcern() {
		return concern;
	}
	public void setConcern(int concern) {
		this.concern = concern;
	}
	@Override
	public String toString() {
		return "ClubVO [cno=" + cno + ", name=" + name + ", description=" + description + ", introduce=" + introduce
				+ ", picturePath=" + picturePath + ", maxPeople=" + maxPeople + ", isprivate=" + isprivate
				+ ", isadmission=" + isadmission + ", lno=" + lno + ", concern=" + concern + "]";
	}
	
	
}
