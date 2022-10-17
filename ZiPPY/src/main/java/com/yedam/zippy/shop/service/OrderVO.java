package com.yedam.zippy.shop.service;

import java.util.Date;

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
  Date OrderDate;
  Date deliveryDate;
}
