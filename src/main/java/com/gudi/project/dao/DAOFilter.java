package com.gudi.project.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.gudi.project.dto.ClothDTO;
import com.gudi.project.dto.ColorDTO;
import com.gudi.project.dto.WeatherDTO;

public interface DAOFilter {

	ArrayList<HashMap<String, String>> submit(HashMap<String, Object> requestMap);

	ArrayList<WeatherDTO> weatherlist();

	ArrayList<ClothDTO> toplist();
}
