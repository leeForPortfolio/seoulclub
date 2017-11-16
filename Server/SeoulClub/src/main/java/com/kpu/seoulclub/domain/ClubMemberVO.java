package com.kpu.seoulclub.domain;

public class ClubMemberVO {
	int cno;
	int uno;
	String name;
	String id;
	int authority;
	
	public ClubMemberVO(int cno, int uno, String name, String id, int authority) {
		super();
		this.cno = cno;
		this.uno = uno;
		this.name = name;
		this.id = id;
		this.authority = authority;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}

}
