package com.kpu.seoulclub.persistence;

import java.util.List;

import com.kpu.seoulclub.domain.ClubVO;

public interface ClubDAO {
	public void create(ClubVO vo) throws Exception;
	
	public void update(ClubVO vo) throws Exception;
	
	public ClubVO read(int cno) throws Exception;
	
	public void delete(int cno) throws Exception;
	
	public List<ClubVO> listAll() throws Exception;
}
