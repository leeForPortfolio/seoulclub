package com.kpu.seoulclub.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kpu.seoulclub.domain.ClubMemberVO;
import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.domain.UserVO;

public interface ClubService {
	public boolean regist(ClubVO vo,ClubMemberVO membervo, MultipartFile file) throws Exception;
	
	public boolean dupCheck(String name) throws Exception;
	
	public List<ClubVO> listAll() throws Exception;
	
	public List<ClubVO> listByCL(String locations, String concerns) throws Exception;
	
	public List<ClubVO> myClubs(int uno) throws Exception;

	public int cnoCount() throws Exception;
	
	public void joinClub(ClubMemberVO membervo) throws Exception;
	
	public void withdrawClub(ClubMemberVO membervo) throws Exception;

}
