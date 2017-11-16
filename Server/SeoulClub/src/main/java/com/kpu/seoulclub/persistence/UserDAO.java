package com.kpu.seoulclub.persistence;

import java.util.List;

import com.kpu.seoulclub.domain.ConcernVO;
import com.kpu.seoulclub.domain.UserVO;

public interface UserDAO {
	public void create(UserVO vo) throws Exception;
	
	public int idCount(String id) throws Exception;
	
	public boolean updatePhoto(int uno, String storedFolder, String storedFile);
	
	public List<ConcernVO> listConcern() throws Exception;
	
	public void update(UserVO vo) throws Exception;
	
	public UserVO read(int uno) throws Exception;
	
	public UserVO read(UserVO vo) throws Exception; //login
	
	public void delete(int uno) throws Exception;
	
	public List<UserVO> listAll() throws Exception;
}
