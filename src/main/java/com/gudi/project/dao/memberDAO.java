package com.gudi.project.dao;

import com.gudi.project.dto.MemberDTO;

public interface memberDAO {

	String login(String id, String pw);

	String overlay(String id);

	int join(String id, String pw, String pw2, String name, String nick, String birth, String phone, String gender);

	String over(String nickName);

	int selectMemNum(String id);

	int profileInsert(int mem_num);

	String find_idForm1(String name, String phone);

	String find_pwForm_2(String name, String id);

	int pwUpdate(String newPw, String id);

	int profilePhoto(int mem_num);

	MemberDTO memberInfo(int mem_num);

	
}
