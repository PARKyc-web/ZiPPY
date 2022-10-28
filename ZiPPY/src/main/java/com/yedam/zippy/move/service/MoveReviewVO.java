package com.yedam.zippy.move.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MoveReviewVO {

  private int reviewNo;
  private String email;
  private String reviewTitle;
  private String reviewContent;
  @JsonFormat(pattern = "yy-MM-dd")
  @DateTimeFormat(pattern="yy-MM-dd")
  private Date reviewDate;
  private int serviceType;
  private String serviceId;
  private int viewCnt;
  private double totalRating;
  private int rate1;
  private int rate2;
  private int rate3;
  private int rate4;
  private int deleteState;
  Integer reviewCount;
  
  double companyTotalRating;
  
  //business
  
  String compName;
  String phone;
  String compAddress;
  String compIntro;
  
}
