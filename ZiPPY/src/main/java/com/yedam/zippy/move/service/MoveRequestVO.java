package com.yedam.zippy.move.service;

import lombok.Data;

@Data
public class MoveRequestVO {

  int estimateNo;
  String email;
  String movingOption;
  String movingMemo;
  
  String departZipCode;
  String departAddress;
  String departDetail;
  String departExtra;

  String arriveZipCode;
  String arriveAddress;
  String arriveDetail;
  String arriveExtra;
  
  String movingDate;
  String movingTime;
  String visitType;
  
//  추가
  String moveType;
}
