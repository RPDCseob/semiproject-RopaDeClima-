package com.gudi.project.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.WeatherDTO;
import com.gudi.project.dto.WriteDTO;
import com.gudi.project.service.listService;

@Controller
public class listController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired listService service;
	// 그냥 내가 상세정보에 쓸려고 만든거
	@ResponseBody	  
	@RequestMapping(value = "/mainlist", method = RequestMethod.GET) 
	public HashMap<String,Object> mainList(HttpServletRequest req, @RequestParam(value="mem_num", required=false) String mem_num) {
		logger.info("메인 리스트 요청"); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		logger.info("메인게시판 리스트 호출"); // DTO 대신 MAP 을 쓰면 어떤 데이터를 가져오는 파악하기 힘들다(DTO 를안만들어도 사용가능) 
		// 소스 파악 등에 힘드니 가급적 쓰지 말것 
		// 언제 쓰나? 여러개의 테이블이 조인하는 등 구조가 복잡한 데이터를가져올 때 
		HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		logger.info("id: "+mem_num);
		ArrayList<WriteDTO> mainList = service.photoList();
		map.put("mainList", mainList);

		return map; 
	}
	
//	@RequestMapping(value = "/detail")
//	public String detail(Model model, @RequestParam String idx) {
//		
//		logger.info("idx = "+idx);
//		// service.detail 에서 BoardDTO 와 PhotoDTO 둘 다 가져와야 한다.
//		// 하지만 반환은 하나만 되므로 model 을 넘겨서 service 에서 model 처리를 하도록 할 예정
//		// model, session, request 등은 인터페이스 이므로 객체화가 안된다. 
//		// service 에서 사용하고 싶다면 컨트롤러에서 받아서 서비스로 넘겨줘야한다.
//		service.detail(idx, model, "detail");
//				
//		return "detail";
//	}
}
