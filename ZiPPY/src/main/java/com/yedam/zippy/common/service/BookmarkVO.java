package com.yedam.zippy.common.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BookmarkVO {
  private int productNo;
  private String productName;
  private int productPrice;
  private String productLocation;
  private int bookmarkNo;
  private String email;
  private int serviceType;
  private String serviceId;
  private int isSell;
  @JsonFormat(pattern = "yy-MM-dd")
  @DateTimeFormat(pattern = "yy-MM-dd")
  private Date productDate;
}
