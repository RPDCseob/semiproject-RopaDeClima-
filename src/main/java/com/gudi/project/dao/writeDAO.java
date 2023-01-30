package com.gudi.project.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gudi.project.dto.ClothDTO;
import com.gudi.project.dto.ColorDTO;
import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.WeatherDTO;
import com.gudi.project.dto.WriteDTO;


public interface writeDAO {

	int write(WriteDTO dto);

	void fileWrite(int idx, String oriFileName, String newFileName);

	void weatherWrite(WeatherDTO dto);

//	void clothWrite(List<String> delList);

	Object list(int offset);

	ArrayList<ClothDTO> toplist();

	ArrayList<ClothDTO> bottomlist();

	ArrayList<ClothDTO> outerlist();

	ArrayList<ClothDTO> dresslist();

	ArrayList<ClothDTO> shoeslist();

	ArrayList<ColorDTO> colorlist();

//	void update(HashMap<String, String> params);

	ArrayList<ClothDTO> list();

	ArrayList<WeatherDTO> weatherlist();
	
	ArrayList<PhotoDTO> fileList(String board_num);

	
	
}
