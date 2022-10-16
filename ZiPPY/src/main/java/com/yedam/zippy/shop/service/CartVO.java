package com.yedam.zippy.shop.service;

import java.util.List;

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
  
  //장바구니 개수
  int countMycart;
  
  //상품정보
  ProductVO productVO;
  String optName;
  int optPrice;
  int cartPrice;
}
