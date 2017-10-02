package com.kpu.seoulclub.domain;

import java.util.Date;

public class BoardVO {
	private int bno;
	private int isnotice;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date moddate;
	private int cno;
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getIsnotice() {
		return isnotice;
	}
	public void setIsnotice(int isnotice) {
		this.isnotice = isnotice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getModdate() {
		return moddate;
	}
	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", isnotice=" + isnotice + ", title=" + title + ", content=" + content
				+ ", writer=" + writer + ", regdate=" + regdate + ", moddate=" + moddate + ", cno=" + cno + "]";
	}
	
}
