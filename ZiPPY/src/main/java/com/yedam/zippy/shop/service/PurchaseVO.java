package com.yedam.zippy.shop.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PurchaseVO {
  private int purNo;
  private String payCode;
  private int purPno;
  private int purOptNo;
  private int purQty;
  private String email;
  private String purPrice;
  private String businessEmail;
  private int orderStatus;
  @JsonFormat(pattern = "yy-MM-dd")
  @DateTimeFormat(pattern="yy-MM-dd")
  private Date orderDate;
  @JsonFormat(pattern = "yy-MM-dd")
  @DateTimeFormat(pattern="yy-MM-dd")
  private Date deliveryDate;
  
  //상품정보
  ProductVO productVO;
  String proName;
  String optName;
  int optPrice;
  
  //주문정보
  String userName;
}
