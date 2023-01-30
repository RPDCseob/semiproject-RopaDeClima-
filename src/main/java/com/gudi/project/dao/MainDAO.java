package com.gudi.project.dao;

import java.util.ArrayList;

import com.gudi.project.dto.MainDTO;
import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.WriteDTO;

public interface MainDAO {

	ArrayList<MainDTO> list();

	ArrayList<MainDTO> allShow();

	ArrayList<MainDTO> recommend(int tmn, int tmx);

	ArrayList<MainDTO> newOrder();

	ArrayList<MainDTO> like();


	ArrayList<PhotoDTO> fileList();

	
	

}
