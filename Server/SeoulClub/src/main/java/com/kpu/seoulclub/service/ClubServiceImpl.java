package com.kpu.seoulclub.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.persistence.ClubDAO;

@Service
public class ClubServiceImpl implements ClubService {

	@Inject
	private ClubDAO dao;
	
	@Override
	public void regist(ClubVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.create(vo);
	}

	@Override
	public void modify(ClubVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public ClubVO read(int cno) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(cno);
	}

	@Override
	public void remove(int cno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(cno);
	}

	@Override
	public List<ClubVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

}
