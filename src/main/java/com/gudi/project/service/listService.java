package com.gudi.project.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudi.project.dao.listDAO;
import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.WriteDTO;

@Service
public class listService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired listDAO dao;
	
	public ArrayList<WriteDTO> photoList() {
		logger.info("메인 리스트 서비스 호출");
		
		
		return dao.photoList();
	}
}
