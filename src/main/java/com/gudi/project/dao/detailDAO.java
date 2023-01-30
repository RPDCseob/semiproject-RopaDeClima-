package com.gudi.project.dao;

import java.util.ArrayList;

import com.gudi.project.dto.Book_listDTO;
import com.gudi.project.dto.CommentDTO;
import com.gudi.project.dto.Like_listDTO;
import com.gudi.project.dto.MemberDTO;
import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.Pro_photoDTO;
import com.gudi.project.dto.ProfileDTO;
import com.gudi.project.dto.WeatherDTO;
import com.gudi.project.dto.WriteDTO;

public interface detailDAO {

	ArrayList<WriteDTO> photoList();

	WriteDTO detail(String board_num);

	ArrayList<PhotoDTO> fileList(String board_num);
	
	ArrayList<Pro_photoDTO> profileList(String board_num);

	WeatherDTO w_detail(String board_num);
	
	MemberDTO m_detail(String board_num);
	
	ProfileDTO p_detail(String board_num);

	void Like_chk(Like_listDTO likedto);

	Like_listDTO b_Like(String board_num);

	int delete(String board_num);

	void Like_up(String board_num);

	int like_list(String mem_num, String board_num);

	void Like_nochk(Like_listDTO likedto);

	void Like_down(String board_num);

	int book_list(String mem_num, String board_num);

	void Book_nochk(Book_listDTO bookdto);

	void Book_chk(Book_listDTO bookdto);

	ArrayList<CommentDTO> commentDetail(int boardNum);

	int commentAdd(int boardNum, int memNum, String commentID);

	void commentUpdate(int comt_num, String acontent);

	int del(int comt_num);


//	WriteDTO pho_List(String board_num);

//	Object findLike(String board_num, String id);
	
	
}
