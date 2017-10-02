package com.kpu.seoulclub.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kpu.seoulclub.domain.UserVO;
import com.kpu.seoulclub.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Inject
	private UserDAO dao;
	
	@Override
	public void regist(UserVO vo) throws Exception {
		dao.create(vo);
	}
	
	@Override
	public void modify(UserVO vo) throws Exception {
		dao.update(vo);
	}
	
	@Override
	public UserVO read(int uno) throws Exception {
		return dao.read(uno);
	}
	
	@Override
	public void remove(int uno) throws Exception {
		dao.delete(uno);
	}
	
	@Override
	public List<UserVO> listAll() throws Exception {
		return dao.listAll();
	}
}
