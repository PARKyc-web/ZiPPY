package com.yedam.zippy.move.service;

import lombok.Data;

@Data
public class moveRequestVO {

  int estimateNo;
  String userEmail;
  String movingOption;
  String movingMemo;
  String movingDepart;
  String movingArrive;
  String movingDate;
  String movingTime;
  int visitType;
  
//  추가
  int movingType;
}
