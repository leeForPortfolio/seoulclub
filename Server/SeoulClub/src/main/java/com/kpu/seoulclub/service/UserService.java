package com.kpu.seoulclub.service;

import java.util.List;

import com.kpu.seoulclub.domain.UserVO;

public interface UserService {
	public void regist(UserVO vo) throws Exception;
	
	public void modify(UserVO vo) throws Exception;
	
	public UserVO read(int uno) throws Exception;
	
	public void remove(int uno) throws Exception;
	
	public List<UserVO> listAll() throws Exception;
}
