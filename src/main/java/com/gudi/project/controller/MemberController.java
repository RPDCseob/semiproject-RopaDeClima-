package com.gudi.project.controller;

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

import com.gudi.project.dto.MemberDTO;
import com.gudi.project.service.MemberService;

@Controller
public class MemberController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MemberService memberservice;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest req) {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		logger.info("id : " + id + "/" + "pw : " + pw);
		String loginId = memberservice.login(id, pw);
		String page = "index";

		if (loginId != null && !loginId.equals("")) {
			int mem_num = memberservice.selectMemNum(id);
			MemberDTO memberInfo = memberservice.memberInfo(mem_num);
			logger.info("loginId : " + loginId);
			page = "redirect:/";
			HttpSession session = req.getSession();
			session.setAttribute("loginId", loginId);
			session.setAttribute("memNum", mem_num);
		} else {
			model.addAttribute("msg", "아이디 또는 패스워드를 확인해 주세요");
		}

		return page;
	}
	
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public String joinForm(Model model) {		
		

		return "join";
	}
	
	@RequestMapping(value = "/ajaxJoin")
	@ResponseBody
	public HashMap<String, Object> ajaxJoin(@RequestParam HashMap<String, String> params) {

		logger.info("params : {}", params);
		String id = params.get("id");
		String pw = params.get("pw");
		String pw2 = params.get("pw2");
		String name = params.get("name");
		String nick = params.get("nick");
		String birth = params.get("birth");
		String phone = params.get("phone");
		String gender = params.get("gender");
		logger.info(id + pw + pw2 + name + nick + birth + phone + gender);

		// 회원가입 등록
		int row = memberservice.join(id, pw, pw2, name, nick, birth, phone, gender);
		// 회원정보 넘버값 조회
		int mem_num = memberservice.selectMemNum(id);
		// 프로필 등록
		int result = memberservice.profileInsert(mem_num);
		// 프로필 사진 등록
		int profilePhoto = memberservice.profilePhoto(mem_num);
		
		if (row < 0) {

		}

		logger.info("mem_num : " + mem_num);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", row);

		return map;
	}
	
	
	@RequestMapping(value="/overlay")
	@ResponseBody
	public HashMap<String, Object> overlay(@RequestParam String id) {
		boolean overlay = true;
		logger.info("아이디 중복 체크 : "+id);		
		HashMap<String, Object> map = new HashMap<String, Object>();		
		
		overlay = memberservice.overlay(id);
		
		map.put("overlay", overlay);		
		return map;
	}
	
	@RequestMapping(value="/over")
	@ResponseBody
	public HashMap<String, Object> over(@RequestParam String nickName) {
		boolean over = true;
		logger.info("닉네임 중복 체크 : "+nickName);		
		HashMap<String, Object> map = new HashMap<String, Object>();		
		
		over = memberservice.over(nickName);
		
		map.put("over", over);		
		return map;
	}
	
	@RequestMapping(value = "/find_id", method = RequestMethod.GET)
	public String find_id(Model model) {

		return "find_idForm";
	}

	@RequestMapping(value = "/find_idForm_2", method = RequestMethod.POST)
	public String find_idForm(Model model, HttpServletRequest req) {

		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		logger.info("name : " + name + " phone : " + phone);

		String page = "/find_idForm";
		String loginId = memberservice.find_idForm1(name, phone);

		logger.info("loginId : " + loginId);

		if (loginId != null && !loginId.equals("")) {
			page = "loginEmail";
			HttpSession session = req.getSession();
			session.setAttribute("loginId", loginId);
			logger.info(loginId);
		} else {
			model.addAttribute("msg", "입력한 정보가 없습니다.");
		}

		return page;
	}

	@RequestMapping(value = "/find_pw", method = RequestMethod.GET)
	public String find_pw(Model model) {

		return "find_pwForm";
	}

	@RequestMapping(value = "/find_pwForm_2", method = RequestMethod.GET)
	public String find_pwForm_2(Model model, HttpServletRequest req) {

		String name = req.getParameter("name");
		String id = req.getParameter("id");

		logger.info("name : " + name + " id : " + id);
		String page = "find_pwForm";

		String loginPw = memberservice.find_pwForm_2(name, id);
		if (loginPw != null && !loginPw.equals("")) {
			page = "find_pwForm_2";
			req.setAttribute("id", id);
			model.addAttribute("msg", "조회완료!");
			logger.info(loginPw);
		} else {
			model.addAttribute("msg", "입력하신 정보가 없습니다.");
		}

		return page;
	}

	@RequestMapping(value = "/pwUpdate", method = RequestMethod.GET)
	public String pwUpdate(Model model, HttpServletRequest req) {

		String newPw = req.getParameter("newPw");
		String id = req.getParameter("id");

		logger.info(newPw + "/" + id);
		String page = "find_pwForm_2";

		int pwUpt = memberservice.pwUpdate(newPw, id);
		logger.info("pwUpt : " + pwUpt);
		if (pwUpt > 0) {
			page = "index";

		} else {
			model.addAttribute("msg", "사용 가능하지 않은 비밀번호입니다.");
		}

		return page;
	}
	
	

}
