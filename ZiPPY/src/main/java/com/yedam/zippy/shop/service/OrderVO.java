package com.yedam.zippy.shop.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderVO {
  int OrderNo;
  String email;
  String payCode;
  String payMethod;
  String amount;
  String buyerName;
  String buyerTel;
  String buyerAddr;
  String buyerZipcode;
  String orderMemo;
  String orderStatus;
  @JsonFormat(pattern = "yy-MM-dd")
  @DateTimeFormat(pattern="yy-MM-dd")
  Date orderDate;
//  @JsonFormat(pattern = "yy-MM-dd")
//  @DateTimeFormat(pattern="yy-MM-dd")
//  Date deliveryDate;
  List<ProductVO> productVO;
}
