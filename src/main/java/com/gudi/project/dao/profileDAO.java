package com.gudi.project.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.gudi.project.dto.MemberDTO;
import com.gudi.project.dto.PhotoDTO;
import com.gudi.project.dto.Pro_photoDTO;
import com.gudi.project.dto.ProfileDTO;
import com.gudi.project.dto.WriteDTO;

public interface profileDAO {

   ProfileDTO profile(String mem_num);
   
   ArrayList<Pro_photoDTO> fileList(String mem_num);

   void fileWrite(String mem_num, String oriFileName, String newFileName);

   ArrayList<PhotoDTO> post_phoList(String mem_num);
   
   ArrayList<PhotoDTO> post_likeList(String mem_num);
   
   ArrayList<PhotoDTO> post_bookList(String mem_num);

   int edit(ProfileDTO dto);

   int tal(String mem_num);

   int totalCount();

   ArrayList<HashMap<String, Object>> list(int offset);

   MemberDTO nick(String mem_num);

WriteDTO post(String mem_num);


}