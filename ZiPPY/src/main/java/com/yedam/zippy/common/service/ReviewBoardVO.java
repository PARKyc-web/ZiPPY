package com.yedam.zippy.common.service;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewBoardVO {
  private int reviewNo;
  private String email;
  private String reviewTitle;
  private String reviewContent;
  private Date reviewDate;
  private int serviceType;
  private String serviceId;
  private int viewCnt;
  private int totalRating;
  private int rate1;
  private int rate2;
  private int rate3;
  private int rate4;
  private int deleteState;
  
}
