package com.yedam.zippy.move.service;

import java.util.List;

import lombok.Data;

@Data
public class MoveResponseVO {

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
}
