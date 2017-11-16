package com.kpu.seoulclub.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kpu.seoulclub.domain.ClubMemberVO;
import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.domain.UserVO;
import com.kpu.seoulclub.service.ClubService;
import com.kpu.seoulclub.service.UserService;

@RestController
@RequestMapping("/club")
public class ClubController {
	private static final Logger logger = LoggerFactory.getLogger(ClubController.class);
	
	@Inject
	private ClubService service;
	private UserService userService;
	
	/*
	 * 모임 등록
	 * 
	 * @RequestParam("id") String id, @RequestParam("pwd")String pwd,@RequestParam("name")String name, @RequestParam("nickName")String nickName,@RequestParam("birth")String birth,
            @RequestParam("location")String location,@RequestParam("concern")String concern, @RequestParam("sex")int sex, @RequestParam("introduce")String introduce,
	 
	 		#{name},	private String name;
		    #{introduce}, 	private String introduce;
		    #{description},	private String description;
		    #{maxpeople},	private int maxPeople;
		    #{isadmission},
		    #{isprivate},
		    #{storedFolder},		private String storedFolder;
		    #{storedFile},private String storedFile;
		    #{originalFileName},	private String originalFileName;
		    #{fileSize},
		    #{location},	private String location;
		    #{concern},		   		    	private String concern;

	 */
	@PostMapping(path="/regist")
	public ResponseEntity<JSONObject> regist(
//			@RequestBody ClubVO vo
			@RequestParam("name") String name,@RequestParam("introduce")String introduce, @RequestParam("description")String description,
			@RequestParam("location")String location, @RequestParam("concern")String concern, MultipartFile file, HttpSession session) {
		ResponseEntity<JSONObject> entity = null;
		JSONObject obj = new JSONObject();

		
		//int cno, String name, String introduce, String description, int maxPeople, String location,
		//String concern 
		int cnoCount = 0;
		
//		try {
//			cnoCount = service.cnoCount()+1;
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		UserVO user = (UserVO)session.getAttribute("user");
		
		ClubVO vo = new ClubVO(name, introduce, description,50 , location, concern, user.getUno());
		ClubMemberVO clubMemberVO = new ClubMemberVO(cnoCount, user.getUno(), user.getName(), user.getId(), 1);

		logger.info("request received");
		logger.info(vo.getName()+" "+vo.getIntroduce());
		if(file != null) {
			logger.info("originalName: " + file.getOriginalFilename());
			logger.info("size: " + file.getSize());
			logger.info("contentType: " + file.getContentType());
		}
		
		try {
			if(service.regist(vo,clubMemberVO, file)) {
				logger.info(vo.getName() + " 모임이 등록되었습니다.");
				obj.put("result", "success");
				entity = new ResponseEntity<> (obj, HttpStatus.CREATED);

			}
			else {
				logger.info("모임등록-모임명 중복!");		
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
	//
	//
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

	@GetMapping("/list")
	public ResponseEntity<List<ClubVO>> clubs() {
		return null;
	}
	
	
	/*
	 * 내 모임
	 * */
	//@GetMapping("/list/{uno}")
	//public ResponseEntity<List<ClubVO>> myClubs(@PathVariable("uno") int uno) {
	@GetMapping("/list/my")
	public ResponseEntity<List<ClubVO>> myClubs(HttpSession session) {
		ResponseEntity<List<ClubVO>> entity = null;
		List<ClubVO> list = null;
		UserVO loginVO = (UserVO)session.getAttribute("user");
		int uno = loginVO.getUno();
		
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
	
	@PostMapping("/join")
	public ResponseEntity<JSONObject> join(@RequestBody ClubVO clubVO, HttpSession session) {
		ResponseEntity<JSONObject> entity = null;
		JSONObject obj = new JSONObject();
		
		try {
			UserVO loginVO = (UserVO)session.getAttribute("user");
			ClubMemberVO clubMemberVO = new ClubMemberVO(clubVO.getCno(), loginVO.getUno(), loginVO.getName(), loginVO.getId(), 0);
			service.joinClub(clubMemberVO);

			obj.put("result", "succ");
			entity = new ResponseEntity<> (obj, HttpStatus.CREATED);
		} catch (Exception e) {
			obj.put("result", "fail");
			entity = new ResponseEntity<> (obj, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	//
	@PostMapping("/unregist")
	public ResponseEntity<JSONObject> unregist(@RequestBody ClubVO clubVO, HttpSession session) {
		ResponseEntity<JSONObject> entity = null;
		JSONObject obj = new JSONObject();
		try {
			UserVO loginVO = (UserVO)session.getAttribute("user");
			ClubMemberVO clubMemberVO = new ClubMemberVO(clubVO.getCno(), loginVO.getUno(), null, null, 0);
			service.withdrawClub(clubMemberVO);
			
			obj.put("result", "succ");
			entity = new ResponseEntity<> (obj, HttpStatus.CREATED);
		} catch (Exception e) {
			obj.put("result", "fail");
			entity = new ResponseEntity<> (obj, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
}
