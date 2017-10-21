package com.kpu.seoulclub.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class UserVO {
	private int uno;
	private String id;
	private String pwd;
	private String name;
	private String nickName;
	private int sex;
	private String introduce;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	
	private String storedFolder;
	private String storedFile;
	private String originalFileName;
	private Date uploadDate;
	private long fileSize;
	private Date regDate;
	private String location;
	private String concern;
	private String imgurl;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
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
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	@Override
	public String toString() {
		return "UserVO [uno=" + uno + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", nickName=" + nickName
				+ ", sex=" + sex + ", introduce=" + introduce + ", birth=" + birth + ", storedFolder=" + storedFolder
				+ ", storedFile=" + storedFile + ", originalFileName=" + originalFileName + ", uploadDate=" + uploadDate
				+ ", fileSize=" + fileSize + ", regDate=" + regDate + ", location=" + location + ", concern=" + concern
				+ ", imgurl=" + imgurl + "]";
	}

	
}
