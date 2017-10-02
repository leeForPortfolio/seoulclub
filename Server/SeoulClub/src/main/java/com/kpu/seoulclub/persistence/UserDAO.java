package com.kpu.seoulclub.persistence;

import java.util.List;

import com.kpu.seoulclub.domain.UserVO;

public interface UserDAO {
	public void create(UserVO vo) throws Exception;
	
	public void update(UserVO vo) throws Exception;
	
	public UserVO read(int uno) throws Exception;
	
	public void delete(int uno) throws Exception;
	
	public List<UserVO> listAll() throws Exception;
}
