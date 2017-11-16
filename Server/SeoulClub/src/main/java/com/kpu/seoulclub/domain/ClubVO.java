package com.kpu.seoulclub.domain;

import java.util.Date;

public class ClubVO {
	private int cno;
	private String name;
	private String introduce;
	private String description;
	private int currentPeople;
	private int maxPeople;
	private int isadmission;
	private int isprivate;
	private String storedFolder;
	private String storedFile;
	private String originalFileName;
	private Date uploadDate;
	private long fileSize;
	private String location;
	private String concern;
	private String imgurl;
	private int uno;
	
	public int getUno() {
		return uno;
	}


	public void setUno(int uno) {
		this.uno = uno;
	}


	public ClubVO() {
		
	}
	
	
	public ClubVO(int cno, String name, String introduce, int currentPeople, int maxPeople, String storedFolder,
			String storedFile, String location, String concern, String imgurl,int uno) {
		super();
		this.cno = cno;
		this.name = name;
		this.introduce = introduce;
		this.currentPeople = currentPeople;
		this.maxPeople = maxPeople;
		this.storedFolder = storedFolder;
		this.storedFile = storedFile;
		this.location = location;
		this.concern = concern;
		this.imgurl = imgurl;
		this.uno = uno;
	}
	public ClubVO(String name, String introduce, String description, int maxPeople, String location,
			String concern,int uno) {
		super();
		this.name = name;
		this.introduce = introduce;
		this.description = description;
		this.maxPeople = maxPeople;
		this.location = location;
		this.concern = concern;
		this.uno = uno;
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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCurrentPeople() {
		return currentPeople;
	}
	public void setCurrentPeople(int currentPeople) {
		this.currentPeople = currentPeople;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	public int getIsadmission() {
		return isadmission;
	}
	public void setIsadmission(int isadmission) {
		this.isadmission = isadmission;
	}
	public int getIsprivate() {
		return isprivate;
	}
	public void setIsprivate(int isprivate) {
		this.isprivate = isprivate;
	}
	public String getStoredFolder() {
		return storedFolder;
	}
	public void setStoredFolder(String storedFolder) {
		this.storedFolder = storedFolder;
	}
	public String getStoredFile() {
		return storedFile;
	}
	public void setStoredFile(String storedFile) {
		this.storedFile = storedFile;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getConcern() {
		return concern;
	}
	public void setConcern(String concern) {
		this.concern = concern;
	}
	
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	@Override
	public String toString() {
		return "ClubVO [cno=" + cno + ", name=" + name + ", introduce=" + introduce + ", description=" + description
				+ ", currentPeople=" + currentPeople + ", maxPeople=" + maxPeople + ", isadmission=" + isadmission
				+ ", isprivate=" + isprivate + ", storedFolder=" + storedFolder + ", storedFile=" + storedFile
				+ ", originalFileName=" + originalFileName + ", uploadDate=" + uploadDate + ", fileSize=" + fileSize
				+ ", location=" + location + ", concern=" + concern + ", imgurl=" + imgurl + "]";
	}
	
	
}
