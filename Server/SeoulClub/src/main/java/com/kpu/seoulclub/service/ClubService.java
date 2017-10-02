package com.kpu.seoulclub.service;

import java.util.List;

import com.kpu.seoulclub.domain.ClubVO;

public interface ClubService {
	public void regist(ClubVO vo) throws Exception;
	
	public void modify(ClubVO vo) throws Exception;
	
	public ClubVO read(int cno) throws Exception;
	
	public void remove(int cno) throws Exception;
	
	public List<ClubVO> listAll() throws Exception;
}
