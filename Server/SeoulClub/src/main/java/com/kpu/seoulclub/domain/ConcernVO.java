package com.kpu.seoulclub.domain;

public class ConcernVO {
	private int cno;
	private String name;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ConcernVO [cno=" + cno + ", name=" + name + "]";
	}
	
	
}
