package com.kpu.seoulclub.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.kpu.seoulclub.domain.ConcernVO;
import com.kpu.seoulclub.domain.UserVO;

public interface UserService {
	public boolean regist(UserVO vo, MultipartFile file) throws Exception;
	
	public UserVO login(UserVO vo) throws Exception;
	
	public boolean dupCheck(String id) throws Exception;
	
	public int UnoCount(String id) throws Exception;
	
	public ResponseEntity<String> modifyPhoto(
			int uno, String storedFolder,
			String storedFile, MultipartFile file) throws Exception;
	
	public void modify(UserVO vo) throws Exception;
	
	public UserVO read(int uno) throws Exception;
	
	public void remove(int uno) throws Exception;
	
	public List<UserVO> listAll() throws Exception;
	
	public List<ConcernVO> listConcern() throws Exception;
}
