package com.kpu.seoulclub.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpu.seoulclub.domain.UserVO;
import com.kpu.seoulclub.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	@PostMapping("/")
	public ResponseEntity<String> regist(@RequestBody UserVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			service.regist(vo);
			entity = new ResponseEntity<String> ("success", HttpStatus.OK);
			logger.info(vo.getId() + "님 등록되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/{uno}")
	public ResponseEntity<UserVO> read(@PathVariable int uno) {
		ResponseEntity<UserVO> entity = null;
		UserVO vo = null;
		
		try {
			vo = service.read(uno);
			entity = new ResponseEntity<UserVO>(vo, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserVO>> listAll() {
		List<UserVO> list = null;
		ResponseEntity<List<UserVO>> entity = null;
		
		
		try {
			list = service.listAll();
			entity = new ResponseEntity<List<UserVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@PutMapping("/{uno}")
	public ResponseEntity<String> modify(@PathVariable int uno, @RequestBody UserVO vo) {
		ResponseEntity<String> entity = null;
		vo.setUno(uno);
		
		try {
			service.modify(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			logger.info(vo.getId() + "님 정보 수정 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@DeleteMapping("/{uno}")
	public ResponseEntity<String> remove(@PathVariable int uno) {
		ResponseEntity<String> entity = null;
		
		try {
			service.remove(uno);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			logger.info(uno + "번 유저 삭제 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
