package com.gudi.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.gudi.project.dto.ClothDTO;
import com.gudi.project.dto.ColorDTO;
import com.gudi.project.dto.WeatherDTO;
import com.gudi.project.dto.WriteDTO;
import com.gudi.project.service.listService;
import com.gudi.project.service.writeService;

@Controller
public class writeController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired writeService service;
	@Autowired listService listService;
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Model model) {
//		logger.info("정상 실행");
//		model.addAttribute("test", "test" );
//
//		return "test2";
//	}



	@RequestMapping(value = "/writeForm")
	public String writeForm(Model model) {
		logger.info("글작성");
		//		ArrayList<ClothDTO> list = service.list();
		//		model.addAttribute("list", list);
		//		HashMap<String, Object> map = new HashMap<String, Object>();
		//		ArrayList<ClothDTO> list = service.list();
		//		map.put("list", list);
		
		return "writeForm2";
	}

	@ResponseBody	  
	@RequestMapping(value = "/weather_listCall", method = RequestMethod.GET) 
	public HashMap<String, Object> weather_List(HttpSession session, HttpServletRequest req) {
		logger.info("list 요청 :"); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		logger.info("날씨 리스트 호출"); // DTO 대신 MAP 을 쓰면 어떤 데이터를 가져오는 파악하기 힘들다(DTO 를안만들어도 사용가능) 
		// 소스 파악 등에 힘드니 가급적 쓰지 말것 
		// 언제 쓰나? 여러개의 테이블이 조인하는 등 구조가 복잡한 데이터를가져올 때 
		ArrayList<WeatherDTO> weather_list = service.weatherlist();
		map.put("weather_list", weather_list);

		return map; 
	} // 날씨 리스트 호출
	
	@ResponseBody	  
	@RequestMapping(value = "/bottomCall", method = RequestMethod.GET) 
	public HashMap<String, Object> bottomlist(HttpSession session) {
		logger.info("list 요청 :"); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		logger.info("게시판 리스트 호출"); // DTO 대신 MAP 을 쓰면 어떤 데이터를 가져오는 파악하기 힘들다(DTO 를안만들어도 사용가능) 
		// 소스 파악 등에 힘드니 가급적 쓰지 말것 
		// 언제 쓰나? 여러개의 테이블이 조인하는 등 구조가 복잡한 데이터를가져올 때 
		ArrayList<ClothDTO> list = service.bottomlist();
		map.put("list", list);
		
		return map; 
	}	//하의 호출
	@ResponseBody	  
	@RequestMapping(value = "/clothCall", method = RequestMethod.GET) 
	public HashMap<String, Object> toplist(HttpSession session) {
		logger.info("list 요청 :"); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		logger.info("게시판 리스트 호출"); // DTO 대신 MAP 을 쓰면 어떤 데이터를 가져오는 파악하기 힘들다(DTO 를안만들어도 사용가능) 
		// 소스 파악 등에 힘드니 가급적 쓰지 말것 
		// 언제 쓰나? 여러개의 테이블이 조인하는 등 구조가 복잡한 데이터를가져올 때 
		ArrayList<ClothDTO> list = service.toplist();
		map.put("list", list);	
		
		
		return map; 
	}	//상의 호출
	@ResponseBody	  
	@RequestMapping(value = "/outerCall", method = RequestMethod.GET) 
	public HashMap<String, Object> outerlist(HttpSession session) {
		logger.info("list 요청 :"); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		logger.info("게시판 리스트 호출"); // DTO 대신 MAP 을 쓰면 어떤 데이터를 가져오는 파악하기 힘들다(DTO 를안만들어도 사용가능) 
		// 소스 파악 등에 힘드니 가급적 쓰지 말것 
		// 언제 쓰나? 여러개의 테이블이 조인하는 등 구조가 복잡한 데이터를가져올 때 
		ArrayList<ClothDTO> list = service.outerlist();
		map.put("list", list);
		
		return map; 
	}	//아우터 호출
	@ResponseBody	  
	@RequestMapping(value = "/dressCall", method = RequestMethod.GET) 
	public HashMap<String, Object> dresslist(HttpSession session) {
		logger.info("list 요청 :"); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		logger.info("게시판 리스트 호출"); // DTO 대신 MAP 을 쓰면 어떤 데이터를 가져오는 파악하기 힘들다(DTO 를안만들어도 사용가능) 
		// 소스 파악 등에 힘드니 가급적 쓰지 말것 
		// 언제 쓰나? 여러개의 테이블이 조인하는 등 구조가 복잡한 데이터를가져올 때 
		ArrayList<ClothDTO> list = service.dresslist();
		map.put("list", list);
		
		return map; 
	}	//원피스 호출
	@ResponseBody	  
	@RequestMapping(value = "/shoesCall", method = RequestMethod.GET) 
	public HashMap<String, Object> shoeslist(HttpSession session) {
		logger.info("list 요청 :"); 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		logger.info("게시판 리스트 호출"); // DTO 대신 MAP 을 쓰면 어떤 데이터를 가져오는 파악하기 힘들다(DTO 를안만들어도 사용가능) 
		// 소스 파악 등에 힘드니 가급적 쓰지 말것 
		// 언제 쓰나? 여러개의 테이블이 조인하는 등 구조가 복잡한 데이터를가져올 때 
		ArrayList<ClothDTO> list = service.shoeslist();
		map.put("list", list);
		
		return map; 
	}	//신발 호출


	@RequestMapping(value = "/write") 
	public String write(MultipartFile photo, HttpServletRequest req, @RequestParam HashMap<String, String> params, @RequestParam(value="mem_num", required=false) String mem_num) { 
		
		HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		logger.info("id: "+mem_num);
		logger.info("params : {}",params); 
//		logger.info("List"+delList);
		logger.info("photo : {}",photo.getOriginalFilename()); 
		//logger.info(""+weather);
		service.write(photo, params,mem_num);
		
		

		return "test2"; 
	}	//글쓰기 작성 완료
//	@RequestMapping(value = "/update") 
//	public String write(@RequestParam HashMap<String, String> params) { 
//		
//		logger.info("params : {}",params); 
////		logger.info("List"+delList);
//		service.update(params);
//		
//		
//		return "writeForm2"; 
//	}
	
	@RequestMapping(value = "/popup") 
	public String popup(Model model, @RequestParam HashMap<String, String> params) { 
		ArrayList<ColorDTO> list = service.colorlist();
		logger.info("list size : "+list.size());
		model.addAttribute("list", list);
			
		return "colorPopup"; 
	}


//	public HashMap<String, Object> colorPop() {
//		
//	HashMap<String, Object> map = new HashMap<String, Object>();
//		
//		logger.info("게시판 리스트 호출"); // DTO 대신 MAP 을 쓰면 어떤 데이터를 가져오는 파악하기 힘들다(DTO 를안만들어도 사용가능) 
//		// 소스 파악 등에 힘드니 가급적 쓰지 말것 
//		// 언제 쓰나? 여러개의 테이블이 조인하는 등 구조가 복잡한 데이터를가져올 때 
//		ArrayList<ColorDTO> list = service.colorlist();
//		map.put("list", list);
//		
//		return map;
//	}
	
//	@RequestMapping(value="/update", method = RequestMethod.POST)
//	public String update(Model model, @RequestParam HashMap<String, String> params) {
//
//		String page = "redirect:/write";
//		
//		logger.info("params : {}", params);
//		service.update(params);
//	
//		return page;
//	}
	
//	@RequestMapping(value = "/write") 
//	public HashMap<String, Object> write(@RequestParam(value = "delList[]") List<String> delList) { 
//		logger.info("List :{} ", delList);
//		//logger.info(""+weather);
//		service.clothWrite(delList);
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		int msg = delList.size();
//		map.put("msg", msg);
//		
//		return map; 
//	}
}
