package com.gudi.project.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gudi.project.dao.writeDAO;
import com.gudi.project.dto.ClothDTO;
import com.gudi.project.dto.ColorDTO;
import com.gudi.project.dto.WeatherDTO;
import com.gudi.project.dto.WriteDTO;



@Service
public class writeService {
	@Autowired writeDAO dao;
	@Autowired ServletContext servletContext;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	public void clothWrite(MultipartFile photo, HashMap<String, String> params) {
//		logger.info("의상 값 넣기");
//		
//		ClothDTO dto = new ClothDTO();
//		int idx = dto.getCloth_num();
//		
//		write(idx, photo, params);
//		
//		dao.clothWrite(dto);
//		
//	}
	
	public String write(MultipartFile photo, HashMap<String, String> params, String mem_num) {
		//logger.info("photo  : {}",photo.getOriginalFilename());	
		
		logger.info("cloth idx : ");
		WriteDTO dto = new WriteDTO();
		dto.setBoard_date(params.get("board_date"));
		dto.setBoard_place(params.get("board_place"));
		dto.setWeather_num(params.get("weather"));
		dto.setTop_num(params.get("topVal"));
		dto.setBottom_num(params.get("bottomVal"));
		dto.setDress_num(params.get("dressVal"));
		dto.setShoes_num(params.get("shoesVal"));
		dto.setOuter_num(params.get("outerVal"));
		dto.setTop_color(params.get("topcolor"));
		dto.setBottom_color(params.get("bottomcolor"));
		dto.setOuter_color(params.get("outercolor"));
		dto.setDress_color(params.get("dresscolor"));
		dto.setShoes_color(params.get("shoescolor"));
		dto.setSortcode(params.get("sortcode"));
		dto.setMem_num(mem_num);
		
		
		logger.info("dto : "+dto.getTop_num());
		int success = dao.write(dto);
		int idx = dto.getBoard_num();
		logger.info("idx:"+idx);
		logger.info("write success : {} ", idx);
//		outfitWrite(idx, params);
//		weatherWrite(idx, params);		

		if(success > 0 && !photo.getOriginalFilename().equals("")) {
			logger.info("업로드 성공");
			fileUpload(photo, idx);
			
		}
		
		return "test";
		
	}
//		public void outfitWrite(int idx, HashMap<String, String> params) {
//			logger.info("착장 넣기");
//			OutfitDTO dto = new OutfitDTO();
//			dto.setCloth_num(params.get("cloth"));
//			logger.info("top : "+params.get("cloth"));
//			
//			dao.outfitWrite(idx, dto);
//				
//		}
	
//	public void weatherWrite(int idx, HashMap<String, String> params) {
//		logger.info("날씨 값 넣기");
//		WeatherDTO dto = new WeatherDTO();
//		String weather = params.get("weather");
//		dto.setWeather_name(params.get("weather") );
//		logger.info("날씨 get :"+weather);
//		dao.weatherWrite(dto);
//		
//	}
	public void fileUpload(MultipartFile photo, int idx) {	
	
		String photo_name = photo.getOriginalFilename();
		logger.info("file idx : "+idx);
		String ext = photo_name.substring(photo_name.lastIndexOf("."));
		
		
		String newPhoto_name = System.currentTimeMillis()+ext;
//		URL resource = getClass().getClassLoader().getResource("리소스 이름");
//		String filePath = resource.getFile();
		try {
			byte[] bytes = photo.getBytes();
//			String fileName = ""; // 파일 이름
//			String web_path = "/resources/img/upload"; // DB 저장될 경로명
//			String absolutePath = request.getServletContext().getRealPath(web_path); // 절대경로
			
//			File file = new File(absolutePath);
			
			
			Path path = Paths.get("C:/upload/"+newPhoto_name);
//			String path = servletContext.getRealPath("/resources");
			Files.write(path, bytes);
			logger.info(newPhoto_name+" UPLOAD OK!");
//			FileCopyUtils.copy()
			dao.fileWrite(idx,photo_name,newPhoto_name);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}

	public ArrayList<ClothDTO> toplist() {
		logger.info("상의 리스트 호출");
		
		
		return dao.toplist();
	}

//	public ArrayList<ClothDTO> toplist() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public ArrayList<ClothDTO> bottomlist() {
		logger.info("하의 리스트 호출");
		
		
		return dao.bottomlist();
	}

	public ArrayList<ClothDTO> outerlist() {
		logger.info("아우터 리스트 호출");
		
		
		return dao.outerlist();
	}

	public ArrayList<ClothDTO> dresslist() {
		logger.info("원피스 리스트 호출");
		
		
		return dao.dresslist();
	}

	public ArrayList<ClothDTO> shoeslist() {
		logger.info("신발 리스트 호출");
		
		
		return dao.shoeslist();
	}

	public ArrayList<ColorDTO> colorlist() {
		logger.info("색 출력");
		return dao.colorlist();
	}

//	public void update(HashMap<String, String> params) {
//		dao.update(params);
//		
//	}

//	public void clothWrite(List<String> delList) {
//		dao.clothWrite(delList);
//		
//	}

	public ArrayList<ClothDTO> list() {
		logger.info("리스트 불러오기 서비스 요청");
		
		return dao.list();
	}

	public ArrayList<WeatherDTO> weatherlist() {
		logger.info(servletContext.getRealPath("/resources"));
		return dao.weatherlist();
	}


}

