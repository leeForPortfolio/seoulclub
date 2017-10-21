package com.kpu.seoulclub.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public final static String IMG_STORE_PATH = "C://seoul//upload";
	// C://Users/~~~
	
	public static void write(String filePath,MultipartFile mf) throws Exception {
		FileCopyUtils.copy(mf.getBytes(), new File(filePath));
	}
	
	public static void delete(String filePath) throws Exception {
		File file = new File(filePath);
		
		if(file.exists()) {
			file.delete();
		}
	}
	
	public static ResponseEntity<byte[]> read(String filePath,HttpHeaders headers) throws Exception{
		
		headers.setContentType(new MediaType("application", "octet-stream"));
		try(FileInputStream fis = new FileInputStream(filePath)) {
			byte b[] = IOUtils.toByteArray(fis);
			return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	public static ResponseEntity<byte[]> controllerGET(String path){
		File f = new File(path);
		if(f.exists()){
			try{
				return FileUtil.read(path, new HttpHeaders());
			}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public static String checkDateFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateFolder = sdf.format(new Date());
		File file = new File(FileUtil.IMG_STORE_PATH+"/"+dateFolder);
		if(!file.exists())
			file.mkdir();
		return dateFolder;
	}
	
	public static String makeFileName(MultipartFile file) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(new Date());
		
		fileName += String.format("%04d", new Random().nextInt(10000));
		
		String extension = file.getOriginalFilename();
		extension = extension.substring(extension.lastIndexOf('.'));
		fileName += extension;
		
		return fileName;
	}
}
