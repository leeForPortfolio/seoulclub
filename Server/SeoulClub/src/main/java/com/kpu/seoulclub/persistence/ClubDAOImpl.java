package com.kpu.seoulclub.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kpu.seoulclub.domain.ClubVO;

@Repository
public class ClubDAOImpl implements ClubDAO {

	@Inject
	SqlSession session;
	
	private static String namespace = "com.kpu.seoulclub.mapper.ClubMapper";
	
	@Override
	public void create(ClubVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(ClubVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace + ".update", vo);
	}

	@Override
	public ClubVO read(int cno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".read", cno);
	}

	@Override
	public void delete(int cno) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace + ".delete", cno);
	}

	@Override
	public List<ClubVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listAll");
	}

}
