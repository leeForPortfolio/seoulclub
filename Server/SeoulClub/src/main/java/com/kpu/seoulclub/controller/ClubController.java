package com.kpu.seoulclub.controller;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.domain.UserVO;
import com.kpu.seoulclub.service.ClubService;

@RestController
@RequestMapping("/club")
public class ClubController {
	private static final Logger logger = LoggerFactory.getLogger(ClubController.class);
	
	@Inject
	private ClubService service;
	
	/*
	 * 모임 등록
	 * */
	@PostMapping(path="/regist", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> regist(@RequestBody ClubVO vo, MultipartFile file) {
		ResponseEntity<String> entity = null;

		logger.info("request received");
		logger.info(vo.getName()+" "+vo.getIntroduce());
		if(file != null) {
			logger.info("originalName: " + file.getOriginalFilename());
			logger.info("size: " + file.getSize());
			logger.info("contentType: " + file.getContentType());
		}
		
		try {
			if(service.regist(vo, file)) {
				logger.info(vo.getName() + " 모임이 등록되었습니다.");
				entity = new ResponseEntity<String> ("success", HttpStatus.CREATED);
			}
			else {
				logger.info("모임등록-모임명 중복!");		
				entity = new ResponseEntity<String> ("fail", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	/*
	 * 모임 중복 체크
	 * */
	@PostMapping(path="/namecheck", produces="application/json; charset=UTF-8")
	public JSONObject idcheck(@RequestBody ClubVO vo) {
		boolean result;
		String name = vo.getName();
		JSONObject obj = new JSONObject();
		
		try {
			if(name != null) {
				result = service.dupCheck(name);
				obj.put("result", result);
			}
			else {
				obj.put("result", "잘못된 요청입니다");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			obj.put("result", "서버 에러");
		}
		
		return obj;
	}
	
	/*
	 * 전체 모임
	 * */
	@GetMapping("/all")
	public ResponseEntity<List<ClubVO>> listAll() {
		ResponseEntity<List<ClubVO>> entity = null;
		List<ClubVO> list = null;
		
		try {
			list = service.listAll();
			entity = new ResponseEntity<List<ClubVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<List<ClubVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	/*
	 * 지역별, 관심사별 목록
	 * */
	@GetMapping("/cl")
	public ResponseEntity<List<ClubVO>> listByCL(String location, String concern) {
		ResponseEntity<List<ClubVO>> entity = null;
		List<ClubVO> list = null;
		
		try {
			list = service.listByCL(location, concern);
			entity = new ResponseEntity<List<ClubVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<List<ClubVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	/*
	 * 내 모임
	 * */
	@GetMapping("/list/{uno}")
	public ResponseEntity<List<ClubVO>> myClubs(@PathVariable("uno") int uno) {
		ResponseEntity<List<ClubVO>> entity = null;
		List<ClubVO> list = null;
		
		try {
			list = service.myClubs(uno);
			entity = new ResponseEntity<List<ClubVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<List<ClubVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
