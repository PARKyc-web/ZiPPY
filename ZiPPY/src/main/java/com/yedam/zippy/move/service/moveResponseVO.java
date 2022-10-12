package com.yedam.zippy.move.service;

import lombok.Data;

@Data
public class moveResponseVO {

  int movingResponseNo;
  int estimateNo;
  String email;
  int firstEstimatePrice;
  String firstEstimateType;
  int secondEstimatePrice;
  String secondEstimateType;
  int reservStatus;
  
}
