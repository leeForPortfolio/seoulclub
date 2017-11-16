package com.kpu.seoulclub.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.kpu.seoulclub.domain.ConcernVO;
import com.kpu.seoulclub.domain.UserVO;
import com.kpu.seoulclub.service.UserService;
import com.kpu.seoulclub.util.FileUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	/*
	 * 회원 가입 요청 예
	 * {
			"id":"gudfo396",
		    "pwd":"123456",
		    "name":"노형래",
		    "nickName":"곰곰곰",
		    "sex":1,
		    "introduce":"안양에 사는 노형래입니다",
		    "birth":"1996-01-24",
		    "location":"강서구, 어저구, 저쩌구",
		    "concern":"여행, 영화, 스터디"
		}
	 * */
	
	@PostMapping(path="/join")
	public ResponseEntity<JSONObject> regist( 
			//@RequestParam UserVO vo,
			@RequestParam("id") String id, @RequestParam("pwd")String pwd,@RequestParam("name")String name, @RequestParam("nickName")String nickName,@RequestParam("birth")String birth,
            @RequestParam("location")String location,@RequestParam("concern")String concern, @RequestParam("sex")int sex, @RequestParam("introduce")String introduce,
		MultipartFile file) throws Exception {
		
		UserVO vo = new UserVO(id, pwd, name, nickName, sex, introduce, birth, location, concern);
		
		ResponseEntity<JSONObject> entity = null;
		JSONObject obj = new JSONObject();

		logger.info("요청이 들어왔습니다.");
		logger.info(vo.getId()+" "+vo.getIntroduce());
		if(file != null) {
			logger.info("originalName: " + file.getOriginalFilename());
			logger.info("size: " + file.getSize());
			logger.info("contentType: " + file.getContentType());
		}
		
		try {
			if(service.regist(vo, file)) {
				logger.info(vo.getId() + "님 등록되었습니다.");
				obj.put("result", "success");
				entity = new ResponseEntity<> (obj, HttpStatus.CREATED);
			}
			else {
				logger.info("회원가입-아이디 중복!");		
				obj.put("result", "fail");
				entity = new ResponseEntity<> (obj, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("result", e.getMessage());
			entity = new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	/*
	 * 아이디 중복 체크
	 * */
	@PostMapping(path="/idcheck", produces="application/json; charset=UTF-8")
	public JSONObject idcheck(@RequestBody UserVO vo) {
		boolean result;
		String id = vo.getId();
		JSONObject obj = new JSONObject();
		
		try {
			if(id != null) {
				result = service.dupCheck(vo.getId());
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
	 * 관심사 리스트
	 * */
	@GetMapping(path="/list/concern")
	public ResponseEntity<List<ConcernVO>> listConcern() {
		ResponseEntity<List<ConcernVO>> entity = null;
		List<ConcernVO> list;
		
		logger.info("/list/concern received");
		try {
			list = service.listConcern();
			entity = new ResponseEntity<List<ConcernVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			entity = new ResponseEntity<List<ConcernVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@GetMapping("/{uno}")
	public ResponseEntity<UserVO> read(@PathVariable("uno") int uno) {
		ResponseEntity<UserVO> entity = null;
		UserVO vo = null;
		
		logger.info(uno + "번 회원 정보 요청");
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
	//
	@GetMapping("/clublist")
	public ResponseEntity<List<UserVO>> clubListAll() {
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
	//
	
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
	
	@PutMapping("/photo/{uno}/{storedFolder}/{storedFile:.+}")
	public ResponseEntity<String> photoPUT(
			@PathVariable("uno") int uno,
			@PathVariable("storedFolder") String storedFolder,
			@PathVariable("storedFile") String storedFile,
			MultipartFile file
			) throws Exception {
		
		return service.modifyPhoto(uno, storedFolder, storedFile, file);
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
	
	@PostMapping("/login")
	public ResponseEntity<JSONObject> login(@RequestBody UserVO vo, HttpSession session) {
		ResponseEntity<JSONObject> entity = null;
		JSONObject obj = new JSONObject();
		
		try {
			UserVO loginVO = service.login(vo);
			session.setAttribute("user", loginVO); 
			obj.put("result", "success");
			entity = new ResponseEntity<>(obj, HttpStatus.OK);
			logger.info(vo.getId() + "님 로그인 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			obj.put("result", "fail");
			entity = new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
