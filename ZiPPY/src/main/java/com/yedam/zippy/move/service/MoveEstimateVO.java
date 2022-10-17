package com.yedam.zippy.move.service;

import lombok.Data;

@Data
public class MoveEstimateVO {

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
  String estimateType;
  String moveType;
  String requestDate;
  String visitDate;
  String visitTime;
  String commonOption;
  
  String dropbox;
  String dropbox2;
}
