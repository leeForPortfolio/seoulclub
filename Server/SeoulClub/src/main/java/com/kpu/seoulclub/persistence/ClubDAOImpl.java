package com.kpu.seoulclub.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kpu.seoulclub.domain.ClubVO;

@Repository
public class ClubDAOImpl implements ClubDAO {

	@Inject
	private SqlSession session;
	
	private static final String namespace = "com.kpu.seoulclub.mapper.ClubMapper";
	
	@Override
	public void create(ClubVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}
	
	@Override
	public int nameCount(String name) throws Exception {
		return session.selectOne(name + ".nameCount");
	}
	
	@Override
	public List<ClubVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public List<ClubVO> listByCL(String location, String concern) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("location", location);
		params.put("concern", concern);
		
		return session.selectList(namespace + ".listByCL", params);
	}
	
	@Override
	public List<ClubVO> listByUno(int uno) throws Exception {
		return session.selectList(namespace + ".listByUno", uno);
	}
}
