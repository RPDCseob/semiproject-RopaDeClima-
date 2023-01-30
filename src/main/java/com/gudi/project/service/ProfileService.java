package com.gudi.project.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.gudi.project.dao.profileDAO;
import com.gudi.project.dto.CommentDTO;
import com.gudi.project.dto.MemberDTO;
import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.Pro_photoDTO;
import com.gudi.project.dto.ProfileDTO;
import com.gudi.project.dto.WriteDTO;


@Service
public class ProfileService {
   @Autowired profileDAO dao;
   
   Logger logger = LoggerFactory.getLogger(this.getClass());
   
   //프로필 페이지 & 내글리스트 요청 서비스
   public void profile(String mem_num, Model model, String method) {
	   
	  WriteDTO w_dto = new WriteDTO();
	  w_dto.setMem_num(mem_num);
	  model.addAttribute("member",w_dto);
      ProfileDTO info = dao.profile(mem_num); // profile 정보
      model.addAttribute("info", info); 
      MemberDTO nick = dao.nick(mem_num); // member 닉네임 정보
      model.addAttribute("nick", nick); 
      ArrayList<Pro_photoDTO> fileList = dao.fileList(mem_num); //프로필 photo 정보
      model.addAttribute("fileList", fileList);
      ArrayList<PhotoDTO> post_phoList = dao.post_phoList(mem_num); // 게시글 photo 정보
      model.addAttribute("post_phoList", post_phoList);
      dao.post(mem_num);
      
   }
   
   // 내 좋아요 리스트 요청 서비스
   public void profileSecond(String mem_num, Model model, String string) {
	   WriteDTO w_dto = new WriteDTO();
		 w_dto.setMem_num(mem_num);
      ProfileDTO info = dao.profile(mem_num); // profile 정보
      model.addAttribute("info", info); 
      MemberDTO nick = dao.nick(mem_num); // member 닉네임 정보
      model.addAttribute("nick", nick); 
      ArrayList<Pro_photoDTO> fileList = dao.fileList(mem_num);//프로필 photo 정보
      model.addAttribute("fileList", fileList);
      ArrayList<PhotoDTO> post_likeList = dao.post_likeList(mem_num); // 좋아요 정보
      model.addAttribute("post_likeList", post_likeList);
      
   }
   
   // 내 북마크 리스트 요청 서비스
   public void profileThird(String mem_num, Model model, String string) {
	   WriteDTO w_dto = new WriteDTO();
		  w_dto.setMem_num(mem_num);
      ProfileDTO info = dao.profile(mem_num);// profile 정보
      info.setMem_num(mem_num);
      model.addAttribute("info", info); 
      MemberDTO nick = dao.nick(mem_num); // member 닉네임 정보
      model.addAttribute("nick", nick); 
      ArrayList<Pro_photoDTO> fileList = dao.fileList(mem_num);//프로필 photo 정보
      model.addAttribute("fileList", fileList);
      ArrayList<PhotoDTO> post_bookList = dao.post_bookList(mem_num); // 북마크 정보
      model.addAttribute("post_bookList", post_bookList);
      
   }
   
   // 내 댓글 리스트 요청 했을 때 프로필 다른 부분 요청..
   public void profileFourth(String mem_num, Model model, String string) {
	   WriteDTO w_dto = new WriteDTO();
		  w_dto.setMem_num(mem_num);
      ProfileDTO info = dao.profile(mem_num); // profile 정보
      model.addAttribute("info", info); 
      MemberDTO nick = dao.nick(mem_num); // member 닉네임 정보
      model.addAttribute("nick", nick); 
      ArrayList<Pro_photoDTO> fileList = dao.fileList(mem_num);//프로필 photo 정보
      model.addAttribute("fileList", fileList);
   }

   // 내 댓글 리스트 요청 서비스
   public HashMap<String, Object> list(int page, String mem_num, HttpServletRequest req) { // 내 댓글 정보
	   HttpSession session = req.getSession();
		mem_num = (String) session.getAttribute("loginId");
		logger.info("id: "+mem_num);
	   
	   WriteDTO w_dto = new WriteDTO();
	   ProfileDTO dto = new ProfileDTO();
	   logger.info("내놔 임마 : "+mem_num);
// 방금수정		 w_dto.setMem_num(mem_num);
		 dto.setMem_num(mem_num);
		 logger.info("혹시 주나 : "+dto.getMem_num());
	   
      int offset = (page-1)*10;
      
      int totalCount=dao.totalCount();
      logger.info("totalCount: "+totalCount);
      
      int totalPages =totalCount%10 > 0 ? (totalCount/10)+1 : (totalCount/10); // 총 페이지 수
      logger.info("총 페이지 수: "+totalPages);
      logger.info("총 페이지 수2: "+Math.ceil(totalCount/10));
      
      HashMap<String, Object> result = new HashMap<String, Object>();
      result.put("total", totalPages);
      result.put("co_list", dao.list(offset));
      
      return result;
   }
   
   
   // 프로필 편집 요청 서비스
   public String edit(Model model,MultipartFile photo, HashMap<String, String> params, String mem_num, HttpServletRequest req) {
      //logger.info("photo 객체: {}", photo.getOriginalFilename());
      
      ProfileDTO dto = new ProfileDTO();
      WriteDTO w_dto = new WriteDTO();
      
      HttpSession session = req.getSession();
      mem_num = (String) session.getAttribute("loginId");
	  w_dto.setMem_num(mem_num);
	  dto.setMem_num(mem_num);
	  
	  logger.info("내놔 : "+w_dto.getMem_num());
	  
	  model.addAttribute("member",w_dto);
      dto.setHeight(params.get("height"));
      dto.setWeight(params.get("weight"));
      
      int success = dao.edit(dto);
      logger.info("write success: {}", dto.getMem_num());
      
      if (success > 0 && !photo.getOriginalFilename().equals("")) {
         fileUpload(photo, dto.getMem_num());
      }
      return "redirect:/";
   }

   // 사진 파일명 추출 서비스
   private void fileUpload(MultipartFile photo, String mem_num) {
      logger.info("파일명 추출");
      WriteDTO w_dto = new WriteDTO();
	  w_dto.setMem_num(mem_num); 
	  
      String oriFileName = photo.getOriginalFilename();
      String ext = oriFileName.substring(oriFileName.lastIndexOf(".")); 
      String newFileName = System.currentTimeMillis() + ext;
      
      try {
         byte[] bytes = photo.getBytes(); 
         Path path = Paths.get("C:/upload/" + newFileName);
         Files.write(path, bytes);
         logger.info(newFileName + "UPLOAD OK!");
         dao.fileWrite(mem_num, oriFileName, newFileName);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   // 탈퇴 요청 서비스
   public void tal(String mem_num) {
      int row = dao.tal(mem_num);
      logger.info("삭제된 척 가려진 행의 갯수: "+row);
   }







   
   
   
}