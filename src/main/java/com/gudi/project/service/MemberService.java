package com.gudi.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudi.project.dao.memberDAO;
import com.gudi.project.dto.MemberDTO;


@Service
public class MemberService {
	
	@Autowired memberDAO dao;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public String login(String id, String pw) {
		logger.info("로그인 서비스");
		return dao.login(id,pw);
	}

	public boolean overlay(String id) {
		String overlayId = dao.overlay(id);
		logger.info("overlay ID : "+overlayId);		
		return overlayId == null ? false : true;
	}

	public int join(String id, String pw, String pw2, String name, String nick, String birth, String phone,
			String gender) {
		logger.info("회원가입 서비스");
		return dao.join(id, pw, pw2, name, nick, birth, phone, gender);
	}

	public boolean over(String nickName) {
		String overNickName = dao.over(nickName);
		logger.info("over nickName : "+overNickName);		
		return overNickName == null ? false : true;
	}

	public int selectMemNum(String id) {
		
		return dao.selectMemNum(id);
	}

	public int profileInsert(int mem_num) {
	
		return dao.profileInsert(mem_num);
	}

	public String find_idForm1(String name, String phone) {
	
		return dao.find_idForm1(name,phone);
	}

	public String find_pwForm_2(String name, String id) {
		logger.info("비밀번호 찾기");
		
		return dao.find_pwForm_2(name,id);
	}

	public int pwUpdate(String newPw, String id) {
		
		return dao.pwUpdate(newPw,id);
	}

	public int profilePhoto(int mem_num) {
		// TODO Auto-generated method stub
		return dao.profilePhoto(mem_num);
	}

	
	 public MemberDTO memberInfo(int mem_num) {
	  
	 return dao.memberInfo(mem_num); }
	 

	

}

