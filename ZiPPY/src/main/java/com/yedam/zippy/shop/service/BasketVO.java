package com.yedam.zippy.shop.service;

import lombok.Data;

@Data
public class BasketVO {
  int basketNo;
  String userEmail;
  int basketProductNo;
  int basketProductOptNo;
  int basketProductQty;
}
