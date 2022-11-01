package com.yedam.zippy.move.service;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MoveCompanyEstimateVO {

  //MoveEstimateVO
  Integer estimateNo;
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
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern="yyyy-MM-dd")
  String movingDate;
  String movingTime;
  String estimateType;
  String moveType;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern="yyyy-MM-dd")
  String requestDate;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern="yyyy-MM-dd")
  String visitDate;
  String visitTime;
  String commonOption;

  
  //MoveResponseVO
  Integer movingResponseNo;
  String businessEmail;
  Integer firstEstimatePrice;
  String firstEstimateType;
  Integer secondEstimatePrice;
  String secondEstimateType;
  Integer reservStatus;
  Integer reservStatuss;
  String compName;
  String responseMemo;
  
}
