package com.yedam.zippy.shop.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
  @DateTimeFormat(pattern="yy-MM-dd")
  Date OrderDate;
  @DateTimeFormat(pattern="yy-MM-dd")
  Date deliveryDate;
}
