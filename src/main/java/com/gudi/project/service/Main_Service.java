package com.gudi.project.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudi.project.dao.MainDAO;
import com.gudi.project.dao.detailDAO;
import com.gudi.project.dao.writeDAO;
import com.gudi.project.dto.MainDTO;
import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.WriteDTO;

@Service
public class Main_Service {

	@Autowired MainDAO dao;
	@Autowired detailDAO detaildao;
	@Autowired writeDAO writedao;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	public ArrayList<MainDTO> list() {
		logger.info("service 접속");
		return dao.list();
	}
	
	public HashMap<String, Object> pho_List() {
		// 간편보기
		logger.info("pho_List 서비스..");

		// 차량정보
		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("post_dto", post_dto);
		ArrayList<MainDTO> fileList = dao.allShow();
		// 해당 ca_num의 photo 데이터 추출 _ 첫번째 사진만 보내기
		// 하나만 전달
			
		map.put("fileList", fileList); 
		logger.info("사진 띄우기"+fileList);
		logger.info("사진 띄우기");
		

		return map;
	}

	public HashMap<String,Object> recommend(int tmn, int tmx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("post_dto", post_dto);

		// 해당 ca_num의 photo 데이터 추출 _ 첫번째 사진만 보내기
		ArrayList<MainDTO> fileList = dao.recommend(tmn, tmx);
		// 하나만 전달
			
		map.put("fileList", fileList); 
		logger.info("추천사진 띄우기"+fileList);
		

		

		return map;
	}

	public HashMap<String, Object> newOrder() {
		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("post_dto", post_dto);


		ArrayList<MainDTO> fileList = dao.newOrder();
			
		map.put("fileList", fileList); 
		logger.info("최신순 띄우기"+fileList);

		

		return map;
	}

	public HashMap<String, Object> like() {
		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("post_dto", post_dto);


		ArrayList<MainDTO> fileList = dao.like();
			
		map.put("fileList", fileList); 
		logger.info("좋아요순 띄우기"+fileList);
		
		return map;
	}

}
