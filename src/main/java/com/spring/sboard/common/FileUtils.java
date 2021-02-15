package com.spring.sboard.common;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {

	@Autowired
	private ServletContext ctx;
	
//	베이스 경로 리턴
	public String getBasePath(String more) {
		return ctx.getRealPath(more);
	}
	
//	경로 폴더 만들기
	public void makeDir(String path) {
		File file = new File(path);
		if(!file.exists()) {
			file.mkdir();
		}
	}
	
//	확장자 리턴
	public String getExt(String fileNm) {
//		return FilenameUtils.getExtension(fileNm);
		return fileNm.substring(fileNm.lastIndexOf('.') + 1);
	}
	
	public String getRandomFileNm() {
		return UUID.randomUUID().toString();
	}
	
	public String getRandomFileNm(String originalFileNm) {
		return getRandomFileNm() + "." + getExt(originalFileNm);
	}
	
	public String saveFile(MultipartFile multiFile, String folder) {
		String basePath = getBasePath(folder);
		makeDir(basePath);
		
		String fileNm = null;
		
		try {
			File file = new File(basePath, fileNm);
			multiFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return fileNm;
	}
}
