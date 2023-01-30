package com.gudi.project.controller;



import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gudi.project.dto.ClothDTO;
import com.gudi.project.dto.WeatherDTO;
import com.gudi.project.service.ServiceFilter;
@Controller
public class ControllerFilter {

   Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired ServiceFilter service;


   @RequestMapping("/submit")
   public void submit(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HashMap<String, Object> resultMap = new HashMap<String, Object>();
      
      HashMap<String, Object> requestMap = new HashMap<String, Object>();
            
         
      try {
         String[] sexArr = request.getParameterValues("sex");      
         requestMap.put("SEX", sexArr);
         for(String data: sexArr) {
        	 logger.info("SEX DATA: "+data);
         }
         } catch (NullPointerException e) {
         }
      try {
         String[] weatherArr = request.getParameterValues("weather");
         requestMap.put("WEATHER", weatherArr);
         for(String data: weatherArr) {
        	 logger.info("weather DATA: "+data);
         }
         } catch (NullPointerException e) {
         }
      try {
         String[] seasonArr = request.getParameterValues("season");
         requestMap.put("SEASON", seasonArr);
         } catch (NullPointerException e) {
         }
      try {
         String[] ageArr = request.getParameterValues("age");
         requestMap.put("AGE", ageArr);
         } catch (NullPointerException e) {
         }
      try {
         String[] termArr = request.getParameterValues("term");
         requestMap.put("TERM", termArr);
         } catch (NullPointerException e) {
         }
      
      
      

      
      try {
         String[] topArr = request.getParameterValues("top");
         requestMap.put("TOP", topArr); 
         for(String data: topArr) {
        	 logger.info("TOP DATA: "+data);
         }
      } catch (NullPointerException e) {
      }
      try {
         String[] bottomArr = request.getParameterValues("bottom");
         requestMap.put("BOTTOM", bottomArr);
      } catch (NullPointerException e) {
      }
      try {
         String[] outerArr = request.getParameterValues("outer");
         requestMap.put("OUTER", outerArr);
      } catch (NullPointerException e) {
      }
      try {
         String[] dressArr = request.getParameterValues("dress");
         requestMap.put("DRESS", dressArr);
      } catch (NullPointerException e) {
      }
      
      try {
         String[] shoesArr = request.getParameterValues("shoes");
         requestMap.put("SHOES", shoesArr);
      } catch (NullPointerException e) {
      }
      
      
      
      
      logger.info("requestMap: "+requestMap);
       ArrayList<HashMap<String,String>> ArrayList = service.submit(requestMap);
       logger.info("ArrayList: "+ArrayList);
       
       resultMap.put("ArrayList", ArrayList);
       JSONObject resultJson = new JSONObject(resultMap);
       
      response.getWriter().print(resultJson);
   }
   
   


   
   
   
   
   @ResponseBody     
      @RequestMapping(value = "/weatherCall", method = RequestMethod.GET) 
      public HashMap<String, Object> weatherlist(HttpSession session) {         
         HashMap<String, Object> map = new HashMap<String, Object>();
         ArrayList<WeatherDTO> weatherlist = service.weatherlist();
         map.put("weatherlist", weatherlist);
         
         return map; 
      }
   
      @ResponseBody     
      @RequestMapping(value = "/topCall", method = RequestMethod.GET) 
      public HashMap<String, Object> toplist(HttpSession session) {
         HashMap<String, Object> map = new HashMap<String, Object>();
         ArrayList<ClothDTO> list = service.toplist();
         map.put("list", list);   
         
         return map; 
      } 
      
      
}