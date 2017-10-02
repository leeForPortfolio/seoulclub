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

import com.kpu.seoulclub.domain.BoardVO;
import com.kpu.seoulclub.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@PostMapping("/")
	public ResponseEntity<String> regist(@RequestBody BoardVO vo) {
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
	
	@GetMapping("/{bno}")
	public ResponseEntity<BoardVO> read(@PathVariable int bno) {
		BoardVO vo = null;
		ResponseEntity<BoardVO> entity = null;
		
		try {
			vo = service.read(bno);
			entity = new ResponseEntity<BoardVO>(vo, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BoardVO>> listAll() {
		List<BoardVO> list = null;
		ResponseEntity<List<BoardVO>> entity = null;
		
		try {
			list = service.listAll();
			entity = new ResponseEntity<List<BoardVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@PutMapping("/{bno}")
	public ResponseEntity<String> modify(@PathVariable int bno, @RequestBody BoardVO vo) {
		ResponseEntity<String> entity = null;
		vo.setBno(bno);
		
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
	
	@DeleteMapping("/{bno}")
	public ResponseEntity<String> remove(@PathVariable int bno) {
		ResponseEntity<String> entity = null;
		
		try {
			service.remove(bno);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
