package com.yedam.zippy.move.service;

import lombok.Data;

@Data
public class MoveContractVO { 
  int contractNo;
  int estimateNo;
  String contractPath;
  String expireDate;
  int reservType;
}