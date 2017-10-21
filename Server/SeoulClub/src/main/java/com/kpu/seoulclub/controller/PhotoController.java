package com.kpu.seoulclub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpu.seoulclub.util.FileUtil;

@RestController
@RequestMapping("/photo")
public class PhotoController {
	private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);
	
	@GetMapping("/{storedFolder}/{storedFile:.+}")
	public ResponseEntity<byte[]> photoGET(
			@PathVariable("storedFolder") String storedFolder,
			@PathVariable("storedFile") String storedFile
			) {
		return FileUtil.controllerGET(FileUtil.IMG_STORE_PATH + "/" + storedFolder + "/" + storedFile);
	}
}
