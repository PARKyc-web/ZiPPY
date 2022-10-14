package com.yedam.zippy.shop.service;

import lombok.Data;

@Data
public class Purchase {
  private int purNo;
  private int payCode;
  private int purPno;
  private int purOptNo;
  private int purQty;
  private String email;
  private String purAmount;
}
