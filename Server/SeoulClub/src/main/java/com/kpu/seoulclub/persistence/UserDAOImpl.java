package com.kpu.seoulclub.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kpu.seoulclub.domain.ConcernVO;
import com.kpu.seoulclub.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.kpu.seoulclub.mapper.UserMapper";
	
	@Override
	public void create(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".create", vo);
	}
	
	@Override
	public int idCount(String id) throws Exception {
		return session.selectOne(namespace + ".idCount", id);
	}
	
	@Override
	public boolean updatePhoto(int uno, String storedFolder, String storedFile) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("uno", uno);
			params.put("storedFolder", storedFolder);
			params.put("storedFile", storedFile);
			session.update(namespace + ".updatePhoto", params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public List<ConcernVO> listConcern() throws Exception {
		return session.selectList(namespace + ".listConcern");
	}

	@Override
	public void update(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace + ".update", vo);
	}

	@Override
	public UserVO read(int uno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".read", uno);
	}

	@Override
	public void delete(int uno) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace + ".delete", uno);
	}

	@Override
	public List<UserVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listAll");
	}
}
