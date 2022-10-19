package com.yedam.zippy.move.service;

import lombok.Data;

@Data
public class MoveCompanyEstimateVO {

  //MoveEstimateVO
  int estimateNo;
  String email;
  String dropbox2;
  String dropbox;
  
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

  
  //MoveResponseVO
  Integer movingResponseNo;

  Integer firstEstimatePrice;
  String firstEstimateType;
  Integer secondEstimatePrice;
  String secondEstimateType;
  Integer reservStatus;
  String compName;
  String responseMemo;
  
}
