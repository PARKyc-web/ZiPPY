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
  
  int countMycart;
  String optName;
  String optPrice;
  
  ProductVO productVO;
  List<ProductOptionVO> option;
}