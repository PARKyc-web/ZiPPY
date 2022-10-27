package com.yedam.zippy.move.service;

import lombok.Data;

@Data
public class MoveMyListVO {

//MoveResponseVO 
  Integer movingResponseNo;
  Integer estimateNo;
  String email;
  Integer firstEstimatePrice;
  String firstEstimateType;
  Integer secondEstimatePrice;
  String secondEstimateType;
  Integer reservStatus;
  String compName;
  String responseMemo;
  
  //rating
  double totalRating;
  Integer serviceType;
  String serviceId;
  
  //business
  String compIntro;
  String phone;
  String compAddress;
  Integer memberType;
  
  //etc
  String dropbox;
  String dropbox2;
  String checked;
  String userEmail;
  String requestDate;
  
}
