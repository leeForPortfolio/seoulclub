package com.kpu.seoulclub.service;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kpu.seoulclub.domain.ClubVO;
import com.kpu.seoulclub.domain.UserVO;
import com.kpu.seoulclub.persistence.ClubDAO;
import com.kpu.seoulclub.util.FileUtil;

@Service
public class ClubServiceImpl implements ClubService {

	@Inject
	private ClubDAO dao;
	
	@Override
	public boolean regist(ClubVO vo, MultipartFile file) throws Exception {
		int nameCount = dao.nameCount(vo.getName());
		
		if(nameCount > 0) 
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
			
			dao.create(vo);
			return true;
		}
	}
	
	public boolean dupCheck(String name) throws Exception {
		if(dao.nameCount(name) > 0)
			return false;
		else
			return true;
	}
	
	@Override
	public List<ClubVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

	@Override
	public List<ClubVO> listByCL(String locations, String concerns) throws Exception {
		String[] locationList = locations.split(",");
		String[] concernList = concerns.split(",");
		
		String location = locationList[new Random().nextInt(locationList.length)].trim();
		String concern = concernList[new Random().nextInt(concernList.length)].trim();
		
		return dao.listByCL(location, concern);
	}
	
	@Override
	public List<ClubVO> myClubs(int uno) throws Exception {
		return dao.listByUno(uno);
	}
}
