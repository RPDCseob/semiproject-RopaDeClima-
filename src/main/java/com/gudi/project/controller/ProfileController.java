package com.gudi.project.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gudi.project.service.ProfileService;
import com.gudi.project.service.detailService;

@Controller
public class ProfileController {
	// 테스트용 클래스
	// 이거 복붙해서 써요
	// 여기다 코드쓰면 바보
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProfileService service;
	@Autowired detailService detailservice;

	//메인 페이지 임의로 test로 설정(현섭님 파트 연결)
	//   @RequestMapping(value = "/")
	//   public String home(Model model) {
	//      logger.info("main");
	//      return "test"; 
	//   }

	//************************************************************************
	@RequestMapping(value = "/logoimg") // 프로필에서 로고이미지 눌렀을 때 메인화면으로 이동
	public String logoimg(Model model) {
		logger.info("main");
		return "redirect:/";
	}
	@RequestMapping(value = "/editLogoimg") // 프로필 페이지에서 로고이미지 눌렀을 때 메인화면으로 이동
	public String editLogoimg(Model model) {
		logger.info("main");
		return "redirect:/";
	}
	//************************************************************************

	// test에서 프로필로 요청
	//프로필 메인화면은 내 글 페이지. (내글,내좋아요,내북마크,내댓글 중)
	@RequestMapping(value = "/profile") 
	public String profile(Model model, HttpServletRequest req, @RequestParam String mem_num) {
		HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		logger.info("mem_num="+ mem_num);
		service.profile(mem_num,model,"profile");
		return "profile";
	}

	//내 좋아요 페이지로 요청 
	@RequestMapping(value = "/profileSecond")
	public String profileSecond(Model model,HttpServletRequest req, @RequestParam String mem_num) {
		HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		logger.info("mem_num="+ mem_num);
		service.profileSecond(mem_num,model,"profileSecond");
		return "profileSecond";
	}

	//내 북마크로 요청 
	@RequestMapping(value = "/profileThird")
	public String profileThird(Model model,HttpServletRequest req, @RequestParam String mem_num) {
		HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		logger.info("mem_num="+ mem_num);
		service.profileThird(mem_num,model,"profileThird");
		return "profileThird";
	}

	//내 댓글페이지 요청할 때 댓글리스트 외 프로필도 띄어야 하는.. 
	@RequestMapping(value = "/profileFourth")
	public String profileFourth(Model model, HttpServletRequest req,@RequestParam String mem_num) {
		logger.info("이몸등장");
		HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		service.profileFourth(mem_num,model,"profileFourth");
		return "profileFourth";
	}

	// 내 댓글리스트 요청
	@RequestMapping(value="/listComment")
	@ResponseBody
	public HashMap<String, Object> Comment_list(HttpServletRequest req,@RequestParam int page, @RequestParam String mem_num) {
		logger.info("저몸등장");
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		logger.info("id: "+mem_num);

		return service.list(page,mem_num,req);
	}


	//프로필 편집 요청
	@RequestMapping(value = "/profileEdit")
	public String profileEdit(Model model, HttpServletRequest req,@RequestParam String mem_num) {
		HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		logger.info("mem_num="+ mem_num);
		service.profile(mem_num,model,"profileEdit");
		return "profileEdit";
	}

	//편집완료 요청
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Model model, MultipartFile photo,  HttpServletRequest req,@RequestParam HashMap<String, String> params) {
		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		
		logger.info("params: {}",params+mem_num);
		logger.info("photo 객체: {}", photo.getOriginalFilename());
		return service.edit(model, photo,params, mem_num, req); 
	}

	// 탈퇴요청
	@RequestMapping(value = "/tal")
	public String tal(HttpServletRequest req,@RequestParam String mem_num) {
		HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		logger.info("mee_num:"+mem_num);
		service.tal(mem_num);
		return "redirect:/";
	}

//	   // 프로필에서 사진 or 댓글 눌렀을 때 상세보기로 넘어감(나영님 파트 연결)
//	   @RequestMapping(value = "/detail")
//	   public String detail() {
//	      return "detail";
//	   }


}