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

import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.service.ClubService;

@RestController
@RequestMapping("/club")
public class ClubController {
	private static final Logger logger = LoggerFactory.getLogger(ClubController.class);
	
	@Inject
	private ClubService service;
	
	@PostMapping("/")
	public ResponseEntity<String> regist(@RequestBody ClubVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			service.regist(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/{cno}")
	public ResponseEntity<ClubVO> read(@PathVariable int cno) {
		ClubVO vo = null;
		ResponseEntity<ClubVO> entity = null;
		
		try {
			vo = service.read(cno);
			entity = new ResponseEntity<ClubVO>(vo, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ClubVO>> listAll() {
		List<ClubVO> list = null;
		ResponseEntity<List<ClubVO>> entity = null;
		
		try {
			list = service.listAll();
			entity = new ResponseEntity<List<ClubVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@PutMapping("/{cno}")
	public ResponseEntity<String> modify(@PathVariable int cno, @RequestBody ClubVO vo) {
		ResponseEntity<String> entity = null;
		vo.setCno(cno);
		
		try {
			service.modify(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@DeleteMapping("/{cno}")
	public ResponseEntity<String> remove(@PathVariable int cno) {
		ResponseEntity<String> entity = null;
		
		try {
			service.remove(cno);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
