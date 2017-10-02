package com.kpu.seoulclub;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kpu.seoulclub.domain.BoardVO;
import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.persistence.BoardDAO;
import com.kpu.seoulclub.persistence.ClubDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Inject
	private BoardDAO dao;
	
	//@Test
	public void createTest() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setIsnotice(0);
		vo.setTitle("testTitle");
		vo.setContent("testContent");
		vo.setWriter("testWriter");
		vo.setCno(1);
		
		try {
			dao.create(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//@Test
	public void updateTest() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setBno(3);
		vo.setIsnotice(0);
		vo.setTitle("modifiedTitle");
		vo.setContent("modifiedContent");
		vo.setWriter("modifiedWriter");
		vo.setCno(1);
		
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
			dao.delete(3);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//@Test
	public void listAllTest() throws Exception {
		try {
			List<BoardVO> list = dao.listAll();
			
			for(BoardVO vo : list) {
				logger.info(vo.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
