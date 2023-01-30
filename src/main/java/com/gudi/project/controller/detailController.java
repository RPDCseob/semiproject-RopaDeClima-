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

import com.gudi.project.dto.CommentDTO;
import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.WeatherDTO;
import com.gudi.project.dto.WriteDTO;
import com.gudi.project.service.detailService;
import com.gudi.project.service.listService;



@Controller
public class detailController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired detailService detailservice;

	@RequestMapping(value = "/detail")
	public String detail(Model model,HttpServletRequest req, @RequestParam String board_num) {

		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		logger.info("상세보기 idx :{}",board_num);
		String page = "redirect:/";
		detailservice.detail(model, board_num, mem_num);
		
		int boardNum = Integer.parseInt(board_num);
		System.out.println("boardNum:" + boardNum);
		
		ArrayList<CommentDTO> commentDetail = detailservice.commentDetail(boardNum);
		model.addAttribute("commentDetail", commentDetail);
		
		model.addAttribute("boardNum", boardNum);
		// model.addAttribute("comtNum", comtNum);

		logger.info("commentDetail : " + commentDetail);

		logger.info("boardNum :" + boardNum);




		page = "detail";



		return page;
	}// 상세정보 출력

	@RequestMapping(value = "/detail/Like_chk")
	public String Like_chk(Model model,HttpServletRequest req, @RequestParam String board_num) {

		logger.info("좋아요 요청");
		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		logger.info("idx :{}",board_num);
		logger.info("좋아요 클릭");
		String page = "redirect:/detail?board_num="+board_num;
		if(detailservice.like_list(board_num, req) == 0) {
			detailservice.Like_chk(board_num, req);
		}else {
			detailservice.Like_down(board_num,req);
			page = "redirect:/detail?board_num="+board_num;
		}
		logger.info("? : "+detailservice.like_list(board_num, req));
		return page;
	}// 좋아요

	// 간편보기
	@RequestMapping(value = "/pho_List")
	@ResponseBody
	public HashMap<String, Object> pho_List(Model model, String board_num){
		logger.info("pho_List 요청..");
		logger.info("board_num :{}", board_num);

		return detailservice.pho_List(board_num);
	}// 상세보기 사진 띄우기

	@RequestMapping(value = "/detail/delete")
	public String delete(Model model, HttpServletRequest req ,@RequestParam String board_num) {		
		logger.info("삭제하기 요청");
		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		
		detailservice.delete(board_num);
		
		return "redirect:/login";
	}// 게시물 삭제하기
	
	@RequestMapping(value = "/detail/Book_chk")
	public String Book_chk(Model model,HttpServletRequest req, @RequestParam String board_num) {

		logger.info("북마크 요청");
		logger.info("idx :{}",board_num);
		logger.info("북마크 클릭");
		String page = "redirect:/detail?board_num="+board_num;
		if(detailservice.book_list(board_num, req) == 0) {
			detailservice.Book_chk(board_num, req);
		}else {
			detailservice.Book_nochk(board_num,req);
			page = "redirect:/detail?board_num="+board_num;
		}
		logger.info("? : "+detailservice.book_list(board_num, req));
		return page;
	}// 북마크

	// 댓글 작성 기능
		@RequestMapping(value = "/commentAdd")
		@ResponseBody
		public HashMap<String, Object> commentAdd(HttpSession session, HttpServletRequest req,@RequestParam("boardNum") int boardNum) {
			HashMap<String, Object> map = new HashMap<String, Object>();

			if (session.getAttribute("memNum") != null) {
				int memNum = Integer.parseInt(req.getParameter("memNum"));
				String commentID = req.getParameter("commentID");
				logger.info("boardNum :" + boardNum + "memNum : " + memNum + "commentID" + commentID);
				int commentAdd = detailservice.commentAdd(boardNum, memNum, commentID);
				
				map.put("commentAdd", commentAdd);
				map.put("login", true);
			} else {
				map.put("login", false);
			}
			return map;
		}

		// 댓글 수정 기능
		@RequestMapping(value = "/updateComment")
		@ResponseBody
		public HashMap<String, Object> updateComment(HttpSession session,
				@RequestParam("comt_num") int comt_num,
				@RequestParam("acontent") String acontent) {
			System.out.println("------------------------------------------");
			HashMap<String, Object> map = new HashMap<String, Object>();

			if (session.getAttribute("memNum") != null) {
				logger.info("comt_num :" + comt_num + "acontent : " + acontent);
				
				detailservice.commentUpdate(comt_num,acontent);
				
				map.put("login", true);
				
			} else {
				map.put("login", false);
			}
			return map;
		}
		
		// 댓글 삭제 기능
		@RequestMapping(value="/del")
		@ResponseBody 
		public HashMap<String, Object> del(HttpSession session, HttpServletRequest req){
			System.out.println("=============================================");
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(session.getAttribute("memNum")!= null){
				System.out.println("==================1111===========================");
				int comt_num = Integer.parseInt(req.getParameter("comt_num"));
				System.out.println("comt_num:+"+comt_num);
				int delete = detailservice.del(comt_num);

				map.put("delete", delete); 
				map.put("login", true); 
			}else { 
				map.put("login",false); 
			} 
			return map; 
		}
	



}
