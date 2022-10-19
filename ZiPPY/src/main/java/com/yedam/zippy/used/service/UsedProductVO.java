package com.yedam.zippy.used.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UsedProductVO {
  private int productNo;
  private String email;
  private String productName;
  private String productCategory;
  private String mainImg;
  private int productPrice;
  private String productInfo;
  private String productLocation;
  private int isSell;
  private String views;
  @JsonFormat(pattern = "yy-MM-dd")
  @DateTimeFormat(pattern = "yy-MM-dd")
  private Date productDate;
  private int imageNo;
  private String image;
  private String nickName;
  private String profileImage;
}
