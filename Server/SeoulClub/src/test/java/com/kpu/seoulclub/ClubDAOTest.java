package com.kpu.seoulclub;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.persistence.ClubDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ClubDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(ClubDAOTest.class);
	
	@Inject
	private ClubDAO dao;
	
	//@Test
	public void createTest() throws Exception {
		ClubVO vo = new ClubVO();
		vo.setName("testClub");
		vo.setMaxPeople(5);
		vo.setLno(3);
		vo.setConcern(5);
		
		try {
			dao.create(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//@Test
	public void updateTest() throws Exception {
		ClubVO vo = new ClubVO();
		vo.setCno(2);
		vo.setName("modifiedClub");
		vo.setMaxPeople(5);
		vo.setLno(3);
		vo.setConcern(5);
		
		try {
			dao.update(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest() throws Exception {
		try {
			dao.delete(2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//@Test
	public void listAllTest() throws Exception {
		try {
			List<ClubVO> list = dao.listAll();
			
			for(ClubVO vo : list) {
				logger.info(vo.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
