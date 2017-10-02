package com.kpu.seoulclub;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kpu.seoulclub.domain.UserVO;
import com.kpu.seoulclub.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class UserDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDAOTest.class);
	
	@Inject
	private UserDAO dao;
	
	//@Test
	public void createTest() throws Exception {
		UserVO vo = new UserVO();
		vo.setId("testID");
		vo.setPwd("test");
		vo.setName("TestName");
		vo.setNickName("TestNickName");
		vo.setSex("남");
		vo.setIntroduce("Test 계정입니다");
		vo.setBirth("1996-01-24");
		vo.setLno(1);
		
		try {
			dao.create(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//@Test
	public void updateTest() throws Exception {
		UserVO vo = new UserVO();
		vo.setUno(2);
		vo.setId("modified ID");
		vo.setPwd("modified test");
		vo.setName("modified TestName");
		vo.setNickName("TestNickName");
		vo.setSex("남");
		vo.setIntroduce("Test 계정입니다");
		vo.setBirth("1996-01-24");
		vo.setLno(1);
		
		try {
			dao.update(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//@Test
	public void deleteTest() throws Exception {
		try {
			dao.delete(2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void listAllTest() throws Exception {
		try {
			List<UserVO> list = dao.listAll();
			
			for(UserVO vo : list) {
				logger.info(vo.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
