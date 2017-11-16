package com.kpu.seoulclub.persistence;

import java.util.List;

import com.kpu.seoulclub.domain.ClubMemberVO;
import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.domain.UserVO;

public interface ClubDAO {
	public void create(ClubVO vo,ClubMemberVO membervo) throws Exception;
	
	public void joinClub(ClubMemberVO memberVO) throws Exception; 

	public void withdrawClub(ClubMemberVO memberVO) throws Exception;
	
	public int nameCount(String name) throws Exception;
	
	
	
	public List<ClubVO> listAll() throws Exception;
	
	public List<ClubVO> listByCL(String location, String concern) throws Exception;
	
	public List<ClubVO> listByUno(int uno) throws Exception;
	
	public int cnoCount() throws Exception;

}
