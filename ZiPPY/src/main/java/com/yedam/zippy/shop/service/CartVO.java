package com.yedam.zippy.shop.service;

import lombok.Data;

@Data
public class CartVO {
  int cartNo;
  String email;
  //int basketProductNo;
  int cartPno;
  //int basketProductOptNo;
  int cartOptNo;
  //int basketProductQty;
  int cartQty;
  
  int countMycart;
  String optName;
  String cartProPrice;
}
