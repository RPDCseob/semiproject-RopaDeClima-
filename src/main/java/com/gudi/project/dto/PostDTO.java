package com.gudi.project.dto;

import java.sql.Date;

public class PostDTO {
	private int board_num;
	private Date board_time;
	private int board_good;
	private String board_place;
	private String board_date;
	private String board_state;
	private String mem_num;
	private String sortcode;
	private String weather_num;
	private int board_tmn;
	private int board_tmx;
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public Date getBoard_time() {
		return board_time;
	}
	public void setBoard_time(Date board_time) {
		this.board_time = board_time;
	}
	public int getBoard_good() {
		return board_good;
	}
	public void setBoard_good(int board_good) {
		this.board_good = board_good;
	}
	public String getBoard_place() {
		return board_place;
	}
	public void setBoard_place(String board_place) {
		this.board_place = board_place;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_state() {
		return board_state;
	}
	public void setBoard_state(String board_state) {
		this.board_state = board_state;
	}
	public String getMem_num() {
		return mem_num;
	}
	public void setMem_num(String mem_num) {
		this.mem_num = mem_num;
	}
	public String getSortcode() {
		return sortcode;
	}
	public void setSortcode(String sortcode) {
		this.sortcode = sortcode;
	}
	public String getWeather_num() {
		return weather_num;
	}
	public void setWeather_num(String weather_num) {
		this.weather_num = weather_num;
	}
	public int getBoard_tmn() {
		return board_tmn;
	}
	public void setBoard_tmn(int board_tmn) {
		this.board_tmn = board_tmn;
	}
	public int getBoard_tmx() {
		return board_tmx;
	}
	public void setBoard_tmx(int board_tmx) {
		this.board_tmx = board_tmx;
	}
}
