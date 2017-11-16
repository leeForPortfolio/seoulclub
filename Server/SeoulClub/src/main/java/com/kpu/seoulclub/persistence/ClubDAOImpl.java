package com.kpu.seoulclub.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kpu.seoulclub.domain.ClubMemberVO;
import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.domain.UserVO;

@Repository
public class ClubDAOImpl implements ClubDAO {

	@Inject
	private SqlSession session;
	
	private static final String namespace = "com.kpu.seoulclub.mapper.ClubMapper";
	
	@Override
	public void create(ClubVO vo,ClubMemberVO membervo) throws Exception {
		session.insert(namespace + ".create", vo);
		membervo.setCno(vo.getCno());
		session.insert(namespace + ".createClubMember", membervo);
	}
	
	@Override
	public void joinClub(ClubMemberVO memberVO) throws Exception {
		session.insert(namespace + ".createClubMember", memberVO);
	}
	
	@Override
	public void withdrawClub(ClubMemberVO memberVO) throws Exception {
		session.delete(namespace + ".deleteClubMember", memberVO);
		int count = session.selectOne(namespace + ".countClubMember", memberVO);
		if( count == 0 ) {
			session.delete(namespace + ".delete", memberVO);
		}
	}
	
	@Override
	public int nameCount(String name) throws Exception {
		return session.selectOne(namespace + ".nameCount", name);
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

	@Override
	public int cnoCount() throws Exception {
		return session.selectOne(namespace +".cnoCount");
	}

//	@Override
//	public void joinClub(UserVO vo) throws Exception {
//		session.insert(namespace + ".create", vo);
//	}

}
