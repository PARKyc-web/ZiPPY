package com.yedam.zippy.shop.service;

import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {
  int OrderNo;
  String userEmail;
  String payCode;
  String payMethod;
  String orderAmount;
  String receiverName;
  String receiverTel;
  String receiverAddress;
  String receiverZipcode;
  String orderMemo;
  String orderStatus;
  Date OrderDate;
  Date deliveryDate;
}
