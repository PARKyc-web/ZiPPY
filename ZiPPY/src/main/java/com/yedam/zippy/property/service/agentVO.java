package com.yedam.zippy.property.service;

import lombok.Data;

@Data
public class agentVO {
  private String email;
  private String compName;
  private String compIntro;
  private String ceoName;
  private String compAddress;
  private String profilePic;
  private String phone;
  private int memberType;
  private int businessState;
  
  private String businessImg;
  private String brokerImg;
  
  private int businessId;
  private int brokerId;
}
