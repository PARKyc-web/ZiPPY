package com.yedam.zippy.shop.service;

import lombok.Data;

@Data
public class PurchaseVO {
  private int purNo;
  private String payCode;
  private int purPno;
  private int purOptNo;
  private int purQty;
  private String email;
  private String purPrice;
  
  //상품정보
  ProductVO productVO;
  String optName;
  int optPrice;
}
