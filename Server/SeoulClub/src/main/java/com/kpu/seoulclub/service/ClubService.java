package com.kpu.seoulclub.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kpu.seoulclub.domain.ClubVO;

public interface ClubService {
	public boolean regist(ClubVO vo, MultipartFile file) throws Exception;
	
	public boolean dupCheck(String name) throws Exception;
	
	public List<ClubVO> listAll() throws Exception;
	
	public List<ClubVO> listByCL(String locations, String concerns) throws Exception;
	
	public List<ClubVO> myClubs(int uno) throws Exception;
}
