package com.gudi.project.dto;

import java.sql.Date;

public class Pro_photoDTO {
   private int mem_num;
   private Date reg_date;
   private String pho_name;
   private String chan_name;
   
   
   public int getMem_num() {
      return mem_num;
   }
   public void setMem_num(int mem_num) {
      this.mem_num = mem_num;
   }
   public Date getReg_date() {
      return reg_date;
   }
   public void setReg_date(Date reg_date) {
      this.reg_date = reg_date;
   }
   public String getPho_name() {
      return pho_name;
   }
   public void setPho_name(String pho_name) {
      this.pho_name = pho_name;
   }
   public String getChan_name() {
      return chan_name;
   }
   public void setChan_name(String chan_name) {
      this.chan_name = chan_name;
   }
   
   
   
}