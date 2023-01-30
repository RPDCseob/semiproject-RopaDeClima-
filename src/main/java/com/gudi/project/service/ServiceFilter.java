package com.gudi.project.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudi.project.dao.DAOFilter;
import com.gudi.project.dto.ClothDTO;
import com.gudi.project.dto.ColorDTO;
import com.gudi.project.dto.WeatherDTO;
@Service
public class ServiceFilter {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired DAOFilter dao;


//
//	public ArrayList<HashMap<String, String>> submit_sex(HashMap<String, String> sex_HashMap) {
//		System.out.println("sex_HashMap 정보 들어갔니ㅠㅠ");
//		logger.info("sex_HashMap: {}",sex_HashMap);
//		return dao.submit(sex_HashMap);
//		
//	}

	public ArrayList<HashMap<String, String>> submit(HashMap<String, Object> requestMap) {
		System.out.println("requestMap 정보 들어갔니ㅠㅠ");
		logger.info("requestMap: {}",requestMap.toString());
		logger.info(" dao.submit(requestMap): {}", dao.submit(requestMap));
		return dao.submit(requestMap);
		
	}
	
	
	
	
	public ArrayList<WeatherDTO> weatherlist() {
	      logger.info("날씨 리스트 호출");
	      return dao.weatherlist();
	   }
	
	public ArrayList<ClothDTO> toplist() {
	      logger.info("상의 리스트 호출");
	      return dao.toplist();
	   }

}
