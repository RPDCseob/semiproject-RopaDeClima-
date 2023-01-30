package com.gudi.project.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gudi.project.dao.detailDAO;
import com.gudi.project.dao.listDAO;
import com.gudi.project.dao.writeDAO;
import com.gudi.project.dto.Book_listDTO;
import com.gudi.project.dto.CommentDTO;
import com.gudi.project.dto.Like_listDTO;
import com.gudi.project.dto.MemberDTO;
import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.Pro_photoDTO;
import com.gudi.project.dto.ProfileDTO;
import com.gudi.project.dto.WeatherDTO;
import com.gudi.project.dto.WriteDTO;

@Service
public class detailService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired detailDAO detaildao;
	@Autowired writeDAO writedao;

	public ArrayList<WriteDTO> photoList() {
		logger.info("메인 리스트 서비스 호출");


		return detaildao.photoList();
	}

	public void detail(Model model,String board_num, String mem_num) {
		logger.info("상세보기");
//		int mem_num = service.selectMemNum(id);
//		
//		HttpSession session = req.getSession();
//		mem_num = (String) session.getAttribute("loginId");
//		session.setAttribute("memNum", memNum);
		
		WriteDTO dto = detaildao.detail(board_num);
		model.addAttribute("detail_post",dto);

		ArrayList<PhotoDTO> post_pho = writedao.fileList(board_num);
		model.addAttribute("post_pho",post_pho);

		WeatherDTO wDTO = detaildao.w_detail(board_num);
		model.addAttribute("detail_weather",wDTO);	

		MemberDTO mDTO = detaildao.m_detail(board_num);
		model.addAttribute("detail_member",mDTO);

		ProfileDTO pDTO = detaildao.p_detail(board_num);
		model.addAttribute("detail_profile",pDTO);

		ArrayList<Pro_photoDTO> pro_photo = detaildao.profileList(board_num);
		model.addAttribute("pro_photo",pro_photo);
		
		Like_listDTO Likedto = new Like_listDTO();
		Likedto.setBoard_num(board_num);
		Likedto.setMem_num(mem_num);
		
		MemberDTO Memdto = new MemberDTO();
		Memdto.setMem_num(mem_num);
		logger.info("상세보기 mem_num : "+mem_num);
		//		model.addAttribute("like",detail.findLike(board_num,id));

		//		Like_listDTO like_DTO = detail.b_Like(board_num);
		//		model.addAttribute("like_list",like_DTO);
		//		like_DTO.setMem_num(id);
		//		detail.Like_chk(board_num);
	}

	public HashMap<String, Object> pho_List(String board_num) {
		// 간편보기
		logger.info("pho_List 서비스..");

		// 차량정보
		WriteDTO post_dto = detaildao.detail(board_num);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("post_dto", post_dto);

		// 해당 ca_num의 photo 데이터 추출 _ 첫번째 사진만 보내기
		ArrayList<PhotoDTO> fileList = writedao.fileList(board_num);
		// 하나만 전달
		if(fileList.size() > 0) { 
			fileList.get(0); 
			map.put("fileList", fileList.get(0)); 
			logger.info("사진 띄우기");
		}

		return map;
	}

	public void delete(String board_num) {
		// 1. 해당 idx 를 부모로 가진 photo 데이터 추출
				ArrayList<PhotoDTO> fileList = writedao.fileList(board_num);
				// 2. 해당 idx 의 bbs 데이터 삭제(on delete cascade)
				int row = detaildao.delete(board_num);
				logger.info("삭제할 post : "+board_num);
				// 3. 삭제가 완료되면 파일 삭제
				if(row>0 && fileList.size()>0) {
					File file = null;
					for(PhotoDTO dto : fileList) {
						file = new File("C:/upload/"+dto.getNewPhoto_name());
						if(file.exists()) {
							logger.info(dto.getNewPhoto_name()+" delete : "+file.delete());
							
						}
					}
				}
		
	}

	public void Like_chk(String board_num, HttpServletRequest req) {
		logger.info("좋아요 서비스");
		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		logger.info("좋아요 mem_num: "+mem_num);
		MemberDTO Memdto = new MemberDTO();
		Memdto.setMem_num(mem_num);
		
		Like_listDTO Likedto = new Like_listDTO();
		Likedto.setMem_num(mem_num);
		Likedto.setBoard_num(board_num);
		detaildao.Like_chk(Likedto);
		detaildao.Like_up(board_num);
		
	}

	public int like_list(String board_num, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		logger.info("좋아요 확인 mem_num: "+mem_num);
		
		Like_listDTO Likedto = new Like_listDTO();
		Likedto.setMem_num(mem_num);
		logger.info("board_num: "+board_num);
		logger.info("mem_num : "+mem_num);
		Likedto.setBoard_num(board_num);
		MemberDTO Memdto = new MemberDTO();
		Memdto.setMem_num(mem_num);
		logger.info("getMem : "+Likedto.getMem_num());
		logger.info("getBoard : "+Likedto.getBoard_num());
	
		int like_list = detaildao.like_list(mem_num,board_num);
		logger.info("like_list : "+like_list);
		
		return like_list;
	}

	public void Like_down(String board_num, HttpServletRequest req) {
		logger.info("좋아요 취소 서비스");
		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		logger.info("좋아요 취소 mem_num: "+mem_num);
		MemberDTO Memdto = new MemberDTO();
		Memdto.setMem_num(mem_num);
		
		Like_listDTO Likedto = new Like_listDTO();
		Likedto.setMem_num(mem_num);
		Likedto.setBoard_num(board_num);
		detaildao.Like_nochk(Likedto);
		detaildao.Like_down(board_num);
		
		
	}

	public int book_list(String board_num, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		logger.info("북마크 확인 mem_num: "+mem_num);
		
		Book_listDTO Bookdto = new Book_listDTO();
		Bookdto.setMem_num(mem_num);
		logger.info("board_num: "+board_num);
		logger.info("mem_num : "+mem_num);
		Bookdto.setBoard_num(board_num);
		MemberDTO Memdto = new MemberDTO();
		Memdto.setMem_num(mem_num);
		logger.info("getMem : "+Bookdto.getMem_num());
		logger.info("getBoard : "+Bookdto.getBoard_num());
	
		int book_list = detaildao.book_list(mem_num,board_num);
		logger.info("like_list : "+book_list);
		
		return book_list;
	}

	public void Book_chk(String board_num, HttpServletRequest req) {
		logger.info("북마크 서비스");
		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		logger.info("북마크 mem_num: "+mem_num);
		MemberDTO Memdto = new MemberDTO();
		Memdto.setMem_num(mem_num);
		
		Book_listDTO Bookdto = new Book_listDTO();
		Bookdto.setMem_num(mem_num);
		Bookdto.setBoard_num(board_num);
		detaildao.Book_chk(Bookdto);
		
	}

	public void Book_nochk(String board_num, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String mem_num = (String) session.getAttribute("loginId");
		
		Book_listDTO Bookdto = new Book_listDTO();
		Bookdto.setMem_num(mem_num);
		Bookdto.setBoard_num(board_num);
		detaildao.Book_nochk(Bookdto);
		
	}

	public ArrayList<CommentDTO> commentDetail(int boardNum) {
		
		return detaildao.commentDetail(boardNum);
	}

	public int commentAdd(int boardNum, int memNum, String commentID) {
		
		return detaildao.commentAdd(boardNum,memNum,commentID);
	}

	public void commentUpdate(int comt_num, String acontent) {
	
		detaildao.commentUpdate(comt_num,acontent);
		
	}

	public int del(int comt_num) {
		
		return detaildao.del(comt_num);
	}

}


