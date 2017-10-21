package com.kpu.seoulclub.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kpu.seoulclub.domain.ConcernVO;
import com.kpu.seoulclub.domain.UserVO;
import com.kpu.seoulclub.persistence.UserDAO;
import com.kpu.seoulclub.util.FileUtil;

@Service
public class UserServiceImpl implements UserService {
	@Inject
	private UserDAO userDao;
	
	@Transactional
	@Override
	public boolean regist(UserVO vo, MultipartFile file) throws Exception {
		int idCount = userDao.idCount(vo.getId());
		
		if(idCount > 0) 
			return false;
		else {
			if(file != null) {
				String dateFolder = FileUtil.checkDateFolder();
				String fileName = FileUtil.makeFileName(file);
				
				FileUtil.write(FileUtil.IMG_STORE_PATH+"/"+dateFolder+"/"+fileName, file);
				
				vo.setOriginalFileName(file.getOriginalFilename());
				vo.setStoredFolder(dateFolder);
				vo.setStoredFile(fileName);
				vo.setFileSize(file.getSize());
			}
			
			userDao.create(vo);
			return true;
		}
	}
	
	public boolean dupCheck(String id) throws Exception {
		System.out.println(id);
		if(userDao.idCount(id) > 0)
			return false;
		else
			return true;
	}
	
	@Transactional
	@Override
	public ResponseEntity<String> modifyPhoto(
			int uno, 
			String storedFolder, 
			String storedFile, MultipartFile file) {
		ResponseEntity<String> entity = null;
		
		try {
			if(file != null) {
				String dateFolder = FileUtil.checkDateFolder();
				String fileName = FileUtil.makeFileName(file);
				
				FileUtil.write(FileUtil.IMG_STORE_PATH+"/"+dateFolder+"/"+fileName, file);
				FileUtil.delete(FileUtil.IMG_STORE_PATH+"/"+storedFolder+"/"+storedFile);
				
				if(userDao.updatePhoto(uno, storedFolder, storedFile)) {
					entity = new ResponseEntity<String>("success", HttpStatus.OK);
				}
				else {
					entity = new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@Override
	public void modify(UserVO vo) throws Exception {
		userDao.update(vo);
	}
	
	@Override
	public UserVO read(int uno) throws Exception {
		return userDao.read(uno);
	}
	
	@Override
	public void remove(int uno) throws Exception {
		userDao.delete(uno);
	}
	
	@Override
	public List<UserVO> listAll() throws Exception {
		return userDao.listAll();
	}
	
	@Override
	public List<ConcernVO> listConcern() throws Exception {
		return userDao.listConcern();
	}
}
