package com.kpu.seoulclub.persistence;

import java.util.List;

import com.kpu.seoulclub.domain.ClubVO;

public interface ClubDAO {
	public void create(ClubVO vo) throws Exception;
	
	public int nameCount(String name) throws Exception;
	
	public List<ClubVO> listAll() throws Exception;
	
	public List<ClubVO> listByCL(String location, String concern) throws Exception;
	
	public List<ClubVO> listByUno(int uno) throws Exception;
}
